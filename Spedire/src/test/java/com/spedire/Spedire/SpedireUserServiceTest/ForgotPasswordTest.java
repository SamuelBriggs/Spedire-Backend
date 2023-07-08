package com.spedire.Spedire.SpedireUserServiceTest;

import com.spedire.Spedire.dtos.request.ForgotPasswordRequest;
import com.spedire.Spedire.dtos.request.PasswordResetRequest;
import com.spedire.Spedire.dtos.request.RegistrationRequest;
import com.spedire.Spedire.dtos.response.ForgotPasswordResponse;
import com.spedire.Spedire.dtos.response.PasswordResetResponse;
import com.spedire.Spedire.dtos.response.RegistrationResponse;
import com.spedire.Spedire.exceptions.EmailNotFoundException;
import com.spedire.Spedire.exceptions.PasswordDoesNotMatchException;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.services.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ForgotPasswordTest {

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
//    @Mock
    private PasswordResetRequest passwordResetRequest = new PasswordResetRequest();
//    @Mock
    private PasswordResetResponse passwordResetResponse = new PasswordResetResponse();


    @BeforeEach
    public void setUp() throws SpedireException {
        userService.saveNewUser("08030669508");
        registrationRequest.setEmail("turdobelma@gufum.com");
        registrationRequest.setFirstName("Aba");
        registrationRequest.setLastName("Abim");
        registrationRequest.setPassword("Password64$");
        userService.register(registrationRequest);
    }

    @Test
    public void forgotPasswordMethodSendMailToUser() throws SpedireException {
        assertNotNull(registrationResponse);
        forgotPasswordRequest.setEmailAddress("retruvukki@gufum.com");
        forgotPasswordResponse = userService.forgotPassword(forgotPasswordRequest);
//        Mockito.when(userService.forgotPassword(forgotPasswordRequest)).thenReturn(forgotPasswordResponse);
        assertNotNull(forgotPasswordResponse);
    }

    @Test
    public void resetPasswordChangeUserPasswordToNewPasswordTest() throws EmailNotFoundException, PasswordDoesNotMatchException {
        passwordResetRequest.setNewPassword("new-password");
        passwordResetRequest.setConfirmPassword("new-password");
        String newPassword = passwordResetRequest.getNewPassword();
        String confirmPassword = passwordResetRequest.getConfirmPassword();
        passwordResetResponse = userService.resetPassword(passwordResetRequest);
//        Mockito.when(userService.resetPassword(passwordResetRequest)).thenReturn(passwordResetResponse);
        assertThat(newPassword).isEqualTo(confirmPassword);
    }
}
