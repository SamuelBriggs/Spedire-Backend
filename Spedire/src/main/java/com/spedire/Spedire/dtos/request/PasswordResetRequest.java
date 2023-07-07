package com.spedire.Spedire.dtos.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PasswordResetRequest {

    private String newEmailAddress;
    private String confirmEmailAddress;

}
