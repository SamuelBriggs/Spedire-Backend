package com.spedire.Spedire.services;

import com.spedire.Spedire.dtos.request.RegistrationRequest;
import com.spedire.Spedire.dtos.response.RegistrationResponse;
import com.spedire.Spedire.exceptions.SpedireException;

public interface UserService {

    RegistrationResponse register(RegistrationRequest Request) throws SpedireException;

    RegistrationResponse checkUserExistence(String request) throws SpedireException;
}
