package com.spedire.Spedire.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetRequest {

    private String newPassword;
    private String confirmPassword;
    private String token;

}
