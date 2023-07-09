package com.spedire.Spedire.sms_sender.config;

import com.nexmo.client.NexmoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.spedire.Spedire.sms_sender.utils.AppUtils.*;


@Configuration
public class NexmoConfig {
    @Value(TWILO_ACCOUNT_SID)
    private String accountSid;
    @Value(TWILO_AUTH_TOKEN)
    private String authToken;
    @Value(TWILO_NUMBER)
    private String twilioNumber;


    @Bean
    public TwilioConfig twilioConfig(){
        return new TwilioConfig(accountSid,authToken,twilioNumber);
    }
}
