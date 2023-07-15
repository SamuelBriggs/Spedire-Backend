package com.spedire.Spedire.exceptions;

import com.spedire.Spedire.exceptions.SpedireException;

public class RegistrationFailedException extends SpedireException {
    public RegistrationFailedException(String message) {
        super(message);
    }
}