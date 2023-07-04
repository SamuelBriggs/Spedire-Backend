package com.spedire.Spedire.services;

import com.spedire.Spedire.dtos.request.RegistrationRequest;
import com.spedire.Spedire.dtos.request.SendEmailRequest;
import com.spedire.Spedire.dtos.response.RegistrationResponse;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.models.User;
import com.spedire.Spedire.repositories.UserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.spedire.Spedire.models.Role.SENDER;
import static com.spedire.Spedire.utils.ResponseUtils.USER_REGISTRATION_SUCCESSFUL;

@Service
@AllArgsConstructor
@Slf4j
public class SpedireUserService implements UserService {
    private final UserRepository userRepository;
    private final EmailService mailService;
    private final PasswordEncoder passwordEncoder;
    public static BrevoEmailService brevoEmailService;

    @Override
    public RegistrationResponse register(RegistrationRequest request) throws SpedireException {
        User user = new User();
        validateRegistrationRequest(request);
        buildRegisterRequest(request, user);
        var savedUser = userRepository.save(user);
        SendEmailRequest emailRequest = brevoEmailService.buildEmailRequest(savedUser);
        mailService.sendMail(emailRequest);
        return buildRegisterResponse(savedUser.getId());
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
    private void validateRegistrationRequest(RegistrationRequest request) throws SpedireException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(request);

        if (!violations.isEmpty()) {
            // Collect validation error messages
            List<String> errorMessages = new ArrayList<>();
            for (ConstraintViolation<RegistrationRequest> violation : violations) {
                errorMessages.add(violation.getMessage());
            }

            throw new SpedireException("Validation error: " + String.join(", ", errorMessages));
        }
    }
}