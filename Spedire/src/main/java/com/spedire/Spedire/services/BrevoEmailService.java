package com.spedire.Spedire.services;

import com.spedire.Spedire.configurations.EmailConfig;
import com.spedire.Spedire.dtos.request.SendEmailRequest;
import com.spedire.Spedire.dtos.response.SendEmailResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static com.spedire.Spedire.utils.EmailConstants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@AllArgsConstructor
@Slf4j
public class BrevoEmailService implements EmailService{
    private final EmailConfig mailConfig;

     @Override
    public SendEmailResponse sendMail(SendEmailRequest emailRequest) {
         System.out.println();
        RestTemplate restTemplate = new RestTemplate();
         HttpHeaders httpHeaders = new HttpHeaders();
         httpHeaders.set(API_KEY_VALUE,mailConfig.getMailApiKey());
         httpHeaders.set("accept", APPLICATION_JSON_VALUE);
         httpHeaders.setContentType(MediaType.APPLICATION_JSON);
         RequestEntity<SendEmailRequest> entity =
                new RequestEntity<>(emailRequest,httpHeaders, HttpMethod.POST, URI.create(EMAIL_URL));
         ResponseEntity<SendEmailResponse> response =
                restTemplate.postForEntity(EMAIL_URL, entity, SendEmailResponse.class);

        return response.getBody();
    }
}