package com.spedire.Spedire.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ForgotPasswordResponse {

    private String message;
    private boolean success;
    private String data;
}
