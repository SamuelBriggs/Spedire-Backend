package com.spedire.Spedire.services;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.spedire.Spedire.dtos.request.ForgotPasswordRequest;
import com.spedire.Spedire.dtos.request.PasswordResetRequest;
import com.spedire.Spedire.dtos.request.RegistrationRequest;
import com.spedire.Spedire.dtos.response.ForgotPasswordResponse;
import com.spedire.Spedire.dtos.response.PasswordResetResponse;
import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.dtos.response.RegistrationResponse;
import com.spedire.Spedire.exceptions.EmailNotFoundException;
import com.spedire.Spedire.exceptions.PasswordResetFailedException;
import com.spedire.Spedire.exceptions.PasswordDoesNotMatchException;


import com.spedire.Spedire.dtos.request.RegistrationRequest;
import com.spedire.Spedire.dtos.request.UpdateUserRequest;
import com.spedire.Spedire.dtos.request.UpgradeUserRequest;
import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.models.User;

public interface UserService {
    ApiResponse<?> register(String token, RegistrationRequest registrationRequest) throws SpedireException;

    RegistrationResponse checkUserExistence(String request) throws SpedireException;

    ForgotPasswordResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest) throws SpedireException;

    PasswordResetResponse resetPassword(PasswordResetRequest passwordResetRequest) throws EmailNotFoundException, PasswordDoesNotMatchException, PasswordResetFailedException;

    User findUserByEmail(String request) throws SpedireException;
    boolean findUserByPhoneNumber(String phoneNumber) throws SpedireException;

    User findByPhoneNumber(String phoneNumber);

    ApiResponse<?> upgradeUserToCarrier(UpgradeUserRequest upgradeUserRequest);

    ApiResponse<?> saveNewUser(String phoneNumber);

    ApiResponse<?> getCurrentUser(String userId) throws SpedireException;

    ApiResponse<?> updateUserDetails(String id, UpdateUserRequest updateUserRequest) throws SpedireException, IllegalAccessException, JsonPointerException;


}
