package com.spedire.Spedire.dtos.response;


import lombok.*;

@Getter
@Setter
@Builder
public class PasswordResetResponse {

    private String message;
    private boolean success;
    private String data;
}
