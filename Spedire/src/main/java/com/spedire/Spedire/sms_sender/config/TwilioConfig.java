package com.spedire.Spedire.sms_sender.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
<<<<<<< HEAD
import org.springframework.context.annotation.Configuration;
=======
import org.springframework.beans.factory.annotation.Value;

import static com.spedire.Spedire.utils.Constants.*;
>>>>>>> 506e99c5c2e601512af8cf6dcd85c62f84b85b57

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
