package com.spedire.Spedire.sms_sender.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TwilioConfig {

    private String accountSid;

    private String authToken;

    private String twilioNumber;
}
