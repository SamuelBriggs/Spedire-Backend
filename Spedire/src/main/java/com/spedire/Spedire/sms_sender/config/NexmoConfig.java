package com.spedire.Spedire.sms_sender.config;

import com.nexmo.client.NexmoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NexmoConfig {
    @Value("${twilio.accountSid}")
    private String accountSid;
    @Value("${twilio.authToken}")
    private String authToken;
    @Value("${twilio.phoneNumber}")
    private String twilioPhoneNumber;
    @Value("${nexmo.creds.api-key}")
    private String VONAGE_API_KEY;
    @Value("${nexmo.creds.secret}")
    private String VONAGE_API_SECRET;
    @Bean
    public NexmoClient nexmoClient(){
        return NexmoClient.builder().apiKey(VONAGE_API_KEY).apiSecret(VONAGE_API_SECRET).build();
    }
    @Bean
    public TwilioConfig twilioConfig(){
        return new TwilioConfig(accountSid,authToken,twilioPhoneNumber);
    }
}
