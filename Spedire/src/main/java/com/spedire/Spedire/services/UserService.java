package com.spedire.Spedire.services;

import com.spedire.Spedire.dtos.request.ForgotPasswordRequest;
import com.spedire.Spedire.dtos.request.PasswordResetRequest;
import com.spedire.Spedire.dtos.request.RegistrationRequest;
import com.spedire.Spedire.dtos.response.ForgotPasswordResponse;
import com.spedire.Spedire.dtos.response.PasswordResetResponse;
import com.spedire.Spedire.dtos.response.RegistrationResponse;
import com.spedire.Spedire.exceptions.EmailNotFoundException;
import com.spedire.Spedire.exceptions.SpedireException;

public interface UserService {

    RegistrationResponse register(RegistrationRequest Request) throws SpedireException;

    RegistrationResponse checkUserExistence(String request) throws SpedireException;

    ForgotPasswordResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest) throws SpedireException;

    PasswordResetResponse resetPassword(PasswordResetRequest passwordResetRequest) throws EmailNotFoundException;
}
