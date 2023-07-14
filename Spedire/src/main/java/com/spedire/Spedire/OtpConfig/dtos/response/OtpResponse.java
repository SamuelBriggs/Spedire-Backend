package com.spedire.Spedire.OtpConfig.dtos.response;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class OtpResponse {
    private String otpNumber;

    private String message;
    private boolean success;

}
