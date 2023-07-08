package com.spedire.Spedire.services;

import com.spedire.Spedire.dtos.request.ForgotPasswordRequest;
import com.spedire.Spedire.dtos.request.PasswordResetRequest;
import com.spedire.Spedire.dtos.request.RegistrationRequest;
import com.spedire.Spedire.dtos.response.*;
import com.spedire.Spedire.exceptions.EmailNotFoundException;
import com.spedire.Spedire.exceptions.PasswordDoesNotMatchException;
import com.spedire.Spedire.exceptions.SpedireException;

public interface UserService {

    RegistrationResponse register(RegistrationRequest Request) throws SpedireException;

    RegistrationResponse checkUserExistence(String request) throws SpedireException;
    boolean findUserByPhoneNumber(String phoneNumber) throws SpedireException;
    ApiResponse saveNewUser(String phoneNumber);

    ForgotPasswordResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest) throws SpedireException;

    PasswordResetResponse resetPassword(PasswordResetRequest passwordResetRequest) throws EmailNotFoundException, PasswordDoesNotMatchException;
}
