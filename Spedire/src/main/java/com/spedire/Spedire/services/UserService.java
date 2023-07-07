package com.spedire.Spedire.services;

import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.spedire.Spedire.dtos.request.RegistrationRequest;
import com.spedire.Spedire.dtos.request.UpdateUserRequest;
import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.dtos.response.RegistrationResponse;
import com.spedire.Spedire.exceptions.SpedireException;

public interface UserService {
    ApiResponse<?> register(String token, RegistrationRequest registrationRequest) throws SpedireException;

    RegistrationResponse checkUserExistence(String request) throws SpedireException;
    boolean findUserByPhoneNumber(String phoneNumber) throws SpedireException;

    ApiResponse<?> updateUserDetails(String id, UpdateUserRequest updateUserRequest)  throws SpedireException;
    ApiResponse<?> saveNewUser(String phoneNumber);

}
