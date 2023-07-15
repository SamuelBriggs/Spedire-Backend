//package com.spedire.Spedire.SpedireUserServiceTest;
//
//import com.spedire.Spedire.dtos.request.ForgotPasswordRequest;
//import com.spedire.Spedire.dtos.request.PasswordResetRequest;
//import com.spedire.Spedire.dtos.request.RegistrationRequest;
//import com.spedire.Spedire.dtos.response.ForgotPasswordResponse;
//import com.spedire.Spedire.dtos.response.RegistrationResponse;
//import com.spedire.Spedire.exceptions.EmailNotFoundException;
//import com.spedire.Spedire.exceptions.PasswordDoesNotMatchException;
//import com.spedire.Spedire.exceptions.PasswordResetFailedException;
//import com.spedire.Spedire.exceptions.SpedireException;
//import com.spedire.Spedire.services.SpedireUserService;
//import com.spedire.Spedire.services.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//@SpringBootTest
//public class PasswordResetTest {
//    @Autowired
//    private UserService userService;
//    private RegistrationRequest registrationRequest = new RegistrationRequest();
//    private RegistrationResponse registrationResponse = new RegistrationResponse();
//    private ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
//    private ForgotPasswordResponse forgotPasswordResponse = new ForgotPasswordResponse();
//    private PasswordResetRequest passwordResetRequest = new PasswordResetRequest();
//
//    @BeforeEach
//    public void setUp() {
//        registrationRequest.setFirstName("Zainab");
//        registrationRequest.setLastName("Plus");
//        registrationRequest.setEmail("alayandezainab64@gmail.com");
//        registrationRequest.setPassword("Zainab87!");
//    }
//
//    @Test
//    public void userForgotPasswordMailToResetPasswordIsSentToUserTest() throws SpedireException {
//        userService.saveNewUser("08181587649");
//        registrationResponse = userService.register(token, registrationRequest);
//        forgotPasswordRequest.setEmailAddress("alayandezainab64@gmail.com");
//        forgotPasswordResponse = userService.forgotPassword(forgotPasswordRequest);
//        assertThat(forgotPasswordResponse).isNotNull();
//    }
//
//
//    @Test
//    public void userCanChangePasswordTest() throws SpedireException {
//        passwordResetRequest.setNewPassword("Abimbola64!");
//        passwordResetRequest.setConfirmPassword("Abimbola64!");
//        passwordResetRequest
//                .setToken("eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2ODg4ODk4MTEsImV4cCI6MTY4ODg5MzQxMSwiaWQiOiI2NGFhNjlkMTAxZjRhNjQzN2UyNjEyNzgifQ.OpqqS4Ftv03RqITQmO73njJklfw1p6xLDuygizji_8AIkjAzonC2wF7KmUK3hh95R-dYvFEaStr1WhOW7f4nPQ");
//        userService.resetPassword(passwordResetRequest);
//    }
//}
