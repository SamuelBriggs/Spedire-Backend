package com.spedire.Spedire.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForgotPasswordResponse {

    private String id;
    private String message;
    private String email;
}
