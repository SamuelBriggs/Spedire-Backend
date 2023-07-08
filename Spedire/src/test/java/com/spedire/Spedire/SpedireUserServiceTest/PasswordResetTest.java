package com.spedire.Spedire.SpedireUserServiceTest;

import com.spedire.Spedire.dtos.request.ForgotPasswordRequest;
import com.spedire.Spedire.dtos.request.PasswordResetRequest;
import com.spedire.Spedire.dtos.request.RegistrationRequest;
import com.spedire.Spedire.dtos.response.ForgotPasswordResponse;
import com.spedire.Spedire.dtos.response.RegistrationResponse;
import com.spedire.Spedire.exceptions.EmailNotFoundException;
import com.spedire.Spedire.exceptions.PasswordResetFailedException;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.services.SpedireUserService;
import com.spedire.Spedire.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class PasswordResetTest {

//    @Mock
    @Autowired
    private UserService userService;
//    @Mock
    private RegistrationRequest registrationRequest = new RegistrationRequest();
//    @Mock
    private RegistrationResponse registrationResponse = new RegistrationResponse();
//    @Mock
    private ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
//    @Mock
    private ForgotPasswordResponse forgotPasswordResponse = new ForgotPasswordResponse();
    private PasswordResetRequest passwordResetRequest = new PasswordResetRequest();

    @BeforeEach
    public void setUp() throws SpedireException {
        userService.saveNewUser("08028765443");
        registrationRequest.setFirstName("you");
        registrationRequest.setLastName("them");
        registrationRequest.setEmail("gakkiterti@gufum.com");
        registrationRequest.setPassword("Password12$");
        registrationResponse = userService.register(registrationRequest);
    }

    @Test
    public void forgotPasswordMethodSendMailToUser() throws SpedireException {
        forgotPasswordRequest.setEmailAddress("gakkiterti@gufum.com");
        forgotPasswordResponse = userService.forgotPassword(forgotPasswordRequest);
        System.out.println("Response -> " + forgotPasswordResponse);
        assertThat(forgotPasswordResponse).isNotNull();
    }

    @Test
    public void userCanChangePasswordTest() throws EmailNotFoundException, PasswordResetFailedException {
        passwordResetRequest.setNewPassword("Abimbola64!");
        passwordResetRequest.setConfirmPassword("Abimbola64!");
        passwordResetRequest.setToken("eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2ODg4Mjc2MzgsImV4cCI6MTY4ODgyNzkzOCwiaWQiOiI2NGE5NzZmMzYxNThlNDExZDYxY2I4MzIifQ.i5lrks4d012rbAGPoV0hCa91vB4S9Ds4zLgNt9ewRVDO5f9jPJBMbCrVeJG6Hd6uEpiJXM-oQw7qsHhQNVA53g");
        userService.resetPassword(passwordResetRequest);
    }
}
