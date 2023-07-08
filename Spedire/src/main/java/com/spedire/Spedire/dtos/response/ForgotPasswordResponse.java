package com.spedire.Spedire.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordResponse {

    private String message;
    private String email;
}
