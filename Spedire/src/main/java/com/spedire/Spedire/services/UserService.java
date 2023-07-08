package com.spedire.Spedire.services;

import com.spedire.Spedire.dtos.request.RegistrationRequest;
import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.dtos.response.FindUserResponse;
import com.spedire.Spedire.dtos.response.RegistrationResponse;
import com.spedire.Spedire.exceptions.SpedireException;

public interface UserService {

    RegistrationResponse register(RegistrationRequest Request) throws SpedireException;

    RegistrationResponse checkUserExistence(String request) throws SpedireException;
    boolean findUserByPhoneNumber(String phoneNumber) throws SpedireException;
    ApiResponse saveNewUser(String phoneNumber);

    ApiResponse getCurrentUser(String phoneNumber);


}
