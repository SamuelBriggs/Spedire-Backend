package com.spedire.Spedire.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String phoneNumber;
    private String password;
}
