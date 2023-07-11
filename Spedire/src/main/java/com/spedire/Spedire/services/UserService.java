package com.spedire.Spedire.services;

import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.spedire.Spedire.dtos.request.RegistrationRequest;
import com.spedire.Spedire.dtos.request.UpdateUserRequest;
import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.models.User;

public interface UserService {
    ApiResponse<?> register(String token, RegistrationRequest registrationRequest) throws SpedireException;

    User findUserByEmail(String request) throws SpedireException;
    boolean findUserByPhoneNumber(String phoneNumber) throws SpedireException;

    User findByPhoneNumber(String phoneNumber);

    ApiResponse<?> saveNewUser(String phoneNumber);

    ApiResponse<?> getCurrentUser(String phoneNumber) throws SpedireException;

    ApiResponse<?> updateUserDetails(String id, UpdateUserRequest updateUserRequest) throws SpedireException, JsonPointerException, IllegalAccessException;


}
