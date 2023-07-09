package com.spedire.Spedire.sms_sender.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import static com.spedire.Spedire.utils.Constants.*;

@AllArgsConstructor
@Getter
public class TwilioConfig {
    @Value(TWILIO_ACCOUNT_SID)
    private String accountSid;

    @Value(TWILIO_AUTH_TOKEN)
    private String authToken;

    @Value(TWILIO_NUMBER)
    private String twilioNumber;

}
