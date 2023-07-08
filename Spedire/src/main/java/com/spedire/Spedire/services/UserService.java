package com.spedire.Spedire.services;

import com.spedire.Spedire.dtos.request.ForgotPasswordRequest;
import com.spedire.Spedire.dtos.request.PasswordResetRequest;
import com.spedire.Spedire.dtos.request.RegistrationRequest;
<<<<<<< HEAD
import com.spedire.Spedire.dtos.response.ForgotPasswordResponse;
import com.spedire.Spedire.dtos.response.PasswordResetResponse;
=======
import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.dtos.response.FindUserResponse;
>>>>>>> 4c179b2c0f4695b92f531e636253a866c3da9bc3
import com.spedire.Spedire.dtos.response.RegistrationResponse;
import com.spedire.Spedire.exceptions.EmailNotFoundException;
import com.spedire.Spedire.exceptions.SpedireException;

public interface UserService {

    RegistrationResponse register(RegistrationRequest Request) throws SpedireException;

    RegistrationResponse checkUserExistence(String request) throws SpedireException;
<<<<<<< HEAD

    ForgotPasswordResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest) throws SpedireException;

    PasswordResetResponse resetPassword(PasswordResetRequest passwordResetRequest) throws EmailNotFoundException;
=======
    boolean findUserByPhoneNumber(String phoneNumber) throws SpedireException;
    ApiResponse saveNewUser(String phoneNumber);
>>>>>>> 4c179b2c0f4695b92f531e636253a866c3da9bc3
}
