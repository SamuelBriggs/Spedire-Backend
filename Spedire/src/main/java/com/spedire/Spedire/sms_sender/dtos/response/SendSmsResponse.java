package com.spedire.Spedire.sms_sender.dtos.response;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class SendSmsResponse {
    private String message;
}
