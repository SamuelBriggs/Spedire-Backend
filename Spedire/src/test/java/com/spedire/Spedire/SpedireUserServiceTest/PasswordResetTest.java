package com.spedire.Spedire.SpedireUserServiceTest;

import com.spedire.Spedire.dtos.request.ForgotPasswordRequest;
import com.spedire.Spedire.dtos.request.RegistrationRequest;
import com.spedire.Spedire.dtos.response.ForgotPasswordResponse;
import com.spedire.Spedire.dtos.response.RegistrationResponse;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.services.SpedireUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PasswordResetTest {

    @Mock
    private SpedireUserService spedireUserService;
    @Mock
    private RegistrationRequest registrationRequest;
    @Mock
    private RegistrationResponse registrationResponse;
    @Mock
    private ForgotPasswordRequest forgotPasswordRequest;
    @Mock
    private ForgotPasswordResponse forgotPasswordResponse;

    @BeforeEach
    public void setUp() throws SpedireException {
        registrationRequest.setPassword("password");
        spedireUserService.register(registrationRequest);


    }

    @Test
    public void forgotPasswordMethodSendMailToUser() {

    }
}
