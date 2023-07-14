package com.spedire.Spedire.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
import com.spedire.Spedire.dtos.request.*;
import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.dtos.response.DashBoardDto;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.models.User;
import com.spedire.Spedire.repositories.UserRepository;
import com.spedire.Spedire.security.JwtUtils;
import com.spedire.Spedire.services.templates.VerifyEmailTemplate;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.spedire.Spedire.models.Role.NEW_USER;
import static com.spedire.Spedire.models.Role.SENDER;
import static com.spedire.Spedire.sms_sender.utils.AppUtils.PHONE_NOT_VALID;
import static com.spedire.Spedire.utils.Constants.*;
import static com.spedire.Spedire.utils.ExceptionUtils.*;
import static com.spedire.Spedire.utils.ResponseUtils.*;
import static java.time.Instant.now;


@Service
@AllArgsConstructor
@Slf4j
public class SpedireUserService implements UserService {
    private final UserRepository userRepository;
    private final EmailService mailService;
    private final CloudService cloudService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtil;
    public static VerifyEmailTemplate verifyEmailTemplate = new VerifyEmailTemplate();

    @Override
    public ApiResponse<?> register(String aToken, RegistrationRequest registrationRequest)
            throws SpedireException {
        String token = aToken.split(" ")[1];
        DecodedJWT decodedJWT = jwtUtil.verifyToken(token);
        String phoneNumber = decodedJWT.getClaim("phoneNumber").asString();
        User foundUser = userRepository.findByPhoneNumber(phoneNumber);
        if (foundUser == null) throw new SpedireException(PHONE_NOT_VALID);
        validateRegistrationRequest(registrationRequest);
        var builtUser = buildRegistrationRequest(registrationRequest, foundUser);
        var savedUser = userRepository.save(builtUser);
        mailService.sendMail(buildEmailRequest(savedUser));
        String newToken = generateJwtToken(savedUser);
        return ApiResponse.builder().message(USER_REGISTRATION_SUCCESSFUL).success(true).data(newToken).build();
    }

    private User buildRegistrationRequest(RegistrationRequest registrationRequest, User foundUser) {
        foundUser.getRoles().add(SENDER);
        foundUser.setFirstName(registrationRequest.getFirstName());
        foundUser.setLastName(registrationRequest.getLastName());
        foundUser.setEmail(registrationRequest.getEmail());
        foundUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        foundUser.setCreatedAt(LocalDateTime.now());
        return foundUser;
    }
    private String generateJwtToken(User registrationResponse) {
        List<String> rolesList = registrationResponse.getRoles()
                .stream()
                .map(Enum::name)
                .collect(Collectors.toList());

        return JWT.create()
                .withIssuedAt(now())
                .withExpiresAt(now().plusSeconds(86400L))
                .withClaim("id", registrationResponse.getId())
                .withClaim("Roles", rolesList)
                .withClaim("phoneNumber", registrationResponse.getPhoneNumber())
                .sign(Algorithm.HMAC512(jwtUtil.getSecret().getBytes()));
    }
    @Override
    public User findUserByEmail(String email) throws SpedireException {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser == null) throw new SpedireException(EMAIL_NOT_FOUND);
        return existingUser;
    }

    @Override
    public ApiResponse<?> saveNewUser(String phoneNumber) {
        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setRoles(new HashSet<>());
        //  user.getRoles().add(NEW_USER);
        user.getRoles().add(NEW_USER);
        var savedUser =userRepository.save(user);
        return ApiResponse.builder().message(NEW_USER_ADDED_SUCCESSFULLY).success(true).data(savedUser.getId()).build();
    }

    @Override
    public ApiResponse<?> getCurrentUser(String phoneNumber) throws SpedireException {
        User foundUser = userRepository.findByPhoneNumber(phoneNumber);
        if (foundUser == null) throw new SpedireException(CURRENT_USER_NOT_FOUND);
        DashBoardDto dashBoardDto = DashBoardDto.builder().
                firstName(foundUser.getFirstName()).
                lastName(foundUser.getLastName()).build();
        return ApiResponse.builder().success(true).message("User Found").data(dashBoardDto).build();
    }

    @Override
    public boolean findUserByPhoneNumber(String phoneNumber) throws SpedireException {
        User foundUser = userRepository.findByPhoneNumber(phoneNumber);
        return foundUser != null;
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    public SendEmailRequest buildEmailRequest(User user) throws SpedireException {
        SendEmailRequest request = new SendEmailRequest();
        Sender sender = new Sender(APP_NAME, APP_EMAIL);
        Recipient recipient = new Recipient(user.getFirstName(), user.getEmail());
        request.setSender(sender);
        request.setRecipients(Set.of(recipient));
        request.setSubject(ACTIVATION_LINK_VALUE);
//        var link =  FRONTEND_BASE_URL+"/user/verify?token="+token;
        request.setContent(verifyEmailTemplate.buildEmail(user.getFirstName(), FRONTEND_BASE_URL));
        return request;
    }

    private void validateRegistrationRequest(RegistrationRequest request) throws SpedireException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(request);

        if (!violations.isEmpty()) {
            List<String> errorMessages = new ArrayList<>();
            for (ConstraintViolation<RegistrationRequest> violation : violations) {
                errorMessages.add(violation.getMessage());
            }

            throw new SpedireException("Validation error: " + String.join(", ", errorMessages));
        }
    }
    @Override
    public ApiResponse<?> updateUserDetails(String aToken, UpdateUserRequest updateUserRequest)
            throws SpedireException, JsonPointerException, IllegalAccessException {
        String token = aToken.split(" ")[1];
        DecodedJWT decodedJWT = jwtUtil.verifyToken(token);
        Optional<User> foundUser = userRepository.
                findUserByPhoneNumber(decodedJWT.getClaim("phoneNumber").asString());
        MultipartFile image = updateUserRequest.getProfileImage();
        JsonPatch jsonPatch = buildUpdatePatch(updateUserRequest);
        User user = foundUser.orElseThrow(()->
                new SpedireException(USER_WITH_ID_NOT_FOUND));
        User updatedUser = updateUser(user, jsonPatch, image);
        User savedUser = userRepository.save(updatedUser);
        return ApiResponse.builder()
                .message(PROFILE_UPDATED_SUCCESSFULLY)
                .success(true).data(savedUser)
                .build();
    }

    private User updateUser(User user, JsonPatch jsonPatch, MultipartFile image) throws SpedireException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        log.info("Patch {}", jsonPatch.toString());
        JsonNode customerNode = mapper.convertValue(user, JsonNode.class);
        try {
            JsonNode updatedNode= jsonPatch.apply(customerNode);

            var updatedUser  =  mapper.convertValue(updatedNode, User.class);
            boolean isProfileImagePresent = image != null;
            if (isProfileImagePresent) uploadImage(image, updatedUser);
            updatedUser.setUpdatedAt(LocalDateTime.now());
            return updatedUser;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SpedireException(PROFILE_UPDATE_FAILED);
        }
    }

    private void uploadImage(MultipartFile image, User user) throws SpedireException,
            IOException, com.spedire.Spedire.Exception.SpedireException {
        String imageUrl = cloudService.upload(image.getBytes());
        user.setProfileImage(imageUrl);
    }


    private JsonPatch buildUpdatePatch(UpdateUserRequest updateUserRequest)
            throws IllegalAccessException, JsonPointerException {
        List<JsonPatchOperation> operations =new ArrayList<>();
        List<String> updateFields =
                List.of("bankName", "accountName", "accountNumber", "email",
                        "password", "profileImage", "firstName", "lastName", "phoneNumber",
                        "streetName", "streetNumber", "landMark", "state", "city");
        Field[] fields = updateUserRequest.getClass().getDeclaredFields();
        for (Field field:fields) {
            field.setAccessible(true);

            if (field.get(updateUserRequest)!=null&&
                    !updateFields.contains(field.getName())){
                var operation = new ReplaceOperation(
                        new JsonPointer("/"+field.getName()),
                        new TextNode(field.get(updateUserRequest).toString())
                );
                operations.add(operation);
            }else if (field.get(updateUserRequest)!=null&&
                    updateFields.contains(field.getName())&&!field.getName().equals("profileImage")){
                ReplaceOperation operation;
                if (field.getName().contains("bank")||field.getName().contains("account")){
                    operation = new ReplaceOperation(
                            new JsonPointer("/bankAccount/" + field.getName()),
                            new TextNode(field.get(updateUserRequest).toString())
                    );
                }else{
                    operation = new ReplaceOperation(
                            new JsonPointer("/address/" + field.getName()),
                            new TextNode(field.get(updateUserRequest).toString())
                    );
                }
                operations.add(operation);
            }
        }
        return new JsonPatch(operations);
    }
    private String updateJwtToken(User registrationResponse) {
        List<String> rolesList = registrationResponse.getRoles()
                .stream()
                .map(Enum::name)
                .collect(Collectors.toList());
        return JWT.create()
                .withIssuedAt(now())
                .withExpiresAt(now().plusSeconds(86400L))
                .withClaim("id", registrationResponse.getId())
                .withClaim("Roles", rolesList)
                .withClaim("phoneNumber", registrationResponse.getPhoneNumber())
                .sign(Algorithm.HMAC512(jwtUtil.getSecret().getBytes()));
    }


}