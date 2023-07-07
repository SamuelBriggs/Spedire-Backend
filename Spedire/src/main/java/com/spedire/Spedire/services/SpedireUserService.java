package com.spedire.Spedire.services;

import com.spedire.Spedire.dtos.request.*;
import com.spedire.Spedire.dtos.response.ForgotPasswordResponse;
import com.spedire.Spedire.dtos.response.PasswordResetResponse;
import com.spedire.Spedire.dtos.response.RegistrationResponse;
import com.spedire.Spedire.exceptions.EmailNotFoundException;
import com.spedire.Spedire.services.templates.ResetPasswordEmailTemplate;
import com.spedire.Spedire.services.templates.VerifyEmailTemplate;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.models.User;
import com.spedire.Spedire.repositories.UserRepository;
import com.spedire.Spedire.utils.JwtUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.spedire.Spedire.models.Role.SENDER;
import static com.spedire.Spedire.services.TokenService.generateToken;
import static com.spedire.Spedire.utils.AppUtils.*;
import static com.spedire.Spedire.utils.EmailConstants.*;
import static com.spedire.Spedire.utils.EmailConstants.FRONTEND_BASE_URL;
import static com.spedire.Spedire.utils.ResponseUtils.PASSWORD_RESET_LINK_SENT_SUCCESSFULLY;
import static com.spedire.Spedire.utils.ResponseUtils.USER_REGISTRATION_SUCCESSFUL;

@Service
@AllArgsConstructor
@Slf4j
public class SpedireUserService implements UserService {
    private final UserRepository userRepository;
    private final EmailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private String email;
    public static VerifyEmailTemplate verifyEmailTemplate = new VerifyEmailTemplate();
    private static ResetPasswordEmailTemplate resetPasswordEmailTemplate = new ResetPasswordEmailTemplate();

    @Override
    public RegistrationResponse register(RegistrationRequest request) throws SpedireException {
        User user = new User();
        checkUserExistence(request.getEmail());
        validateRegistrationRequest(request);
        buildRegisterRequest(request, user);
        var savedUser = userRepository.save(user);
        SendEmailRequest emailRequest = buildRegistrationEmailRequest(savedUser);
        mailService.sendMail(emailRequest);
        return buildRegisterResponse(savedUser.getId());
    }

    @Override
    public RegistrationResponse checkUserExistence(String email) throws SpedireException {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            throw new SpedireException("User with the provided email already exists, Kindly login");
        }
        return null;
    }

    @Override
    public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest) throws SpedireException {
        String emailAddress = forgotPasswordRequest.getEmailAddress();
        User user = userRepository.findByEmail(emailAddress);
        if (user == null) throw new EmailNotFoundException(String.format(emailAddress, NOT_FOUND));
        SendEmailRequest request =  buildResetPasswordEmailRequest(user);
        mailService.sendMail(request);
        return buildForgotPasswordResponse(user.getEmail());
    }

    private ForgotPasswordResponse buildForgotPasswordResponse(String emailAddress) {
        ForgotPasswordResponse response = new ForgotPasswordResponse();
        response.setMessage(String.format(PASSWORD_RESET_LINK_SENT_SUCCESSFULLY, emailAddress));
        response.setEmail(emailAddress);
        email = emailAddress;
        return response;
    }

    private String buildUserEmail() {
        return email;
    }

    @Override
    public PasswordResetResponse resetPassword(PasswordResetRequest passwordResetRequest) throws EmailNotFoundException {
        String userEmail = buildUserEmail();
        User user = userRepository.findByEmail(userEmail);
        if (user == null) throw new EmailNotFoundException(String.format(userEmail, NOT_FOUND));
        user.setPassword(passwordResetRequest.getConfirmEmailAddress());
        userRepository.save(user);
        return buildPasswordResetPassword();
    }

    private PasswordResetResponse buildPasswordResetPassword() {
        PasswordResetResponse response = new PasswordResetResponse();
        response.setMessage(PASSWORD_RESET_SUCCESSFUL);
        return response;
    }


    private void buildRegisterRequest(RegistrationRequest request, User user) {
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Collections.singleton(SENDER));
    }

    private static RegistrationResponse buildRegisterResponse(String userId) {
        RegistrationResponse response = new RegistrationResponse();
        response.setMessage(USER_REGISTRATION_SUCCESSFUL);
        response.setId(userId);
        return response;
    }


    public SendEmailRequest buildRegistrationEmailRequest(User user) throws SpedireException {
        String token = generateToken(user, jwtUtil.secret());
        SendEmailRequest request = new SendEmailRequest();
        Sender sender = new Sender(APP_NAME, APP_EMAIL);
        Recipient recipient = new Recipient(user.getFirstName(), user.getEmail());
        request.setSender(sender);
        request.setRecipients(Set.of(recipient));
        request.setSubject(ACTIVATION_LINK_VALUE);
//        var link =  FRONTEND_BASE_URL+"/user/verify?token="+token;
        var link =  FRONTEND_BASE_URL;
        request.setContent(verifyEmailTemplate.buildEmail(user.getFirstName(), link));
        return request;
    }

    public SendEmailRequest buildResetPasswordEmailRequest(User user) throws SpedireException {
//        String token = generateToken(user, jwtUtil.secret());
        SendEmailRequest request = new SendEmailRequest();
        Sender sender = new Sender(APP_NAME, APP_EMAIL);
        Recipient recipient = new Recipient(user.getFirstName(), user.getEmail());
        request.setSender(sender);
        request.setRecipients(Set.of(recipient));
        request.setSubject(ACTIVATION_LINK_VALUE);
//        var link =  FRONTEND_BASE_URL+"/user/verify?token="+token;
        var link =  PASSWORD_RESET_BASE_URL;
        request.setContent(resetPasswordEmailTemplate.buildEmail(user.getFirstName(), link));
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
}