package com.spedire.Spedire.services;

import com.spedire.Spedire.configurations.EmailConfig;
import com.spedire.Spedire.dtos.request.Recipient;
import com.spedire.Spedire.dtos.request.SendEmailRequest;
import com.spedire.Spedire.dtos.request.Sender;
import com.spedire.Spedire.dtos.response.SendEmailResponse;
import com.spedire.Spedire.dtos.templates.VerifyEmailTemplate;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.models.User;
import com.spedire.Spedire.utils.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Set;

import static com.spedire.Spedire.services.TokenService.generateToken;
import static com.spedire.Spedire.utils.Constants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@AllArgsConstructor
@Slf4j
public class BrevoEmailService implements EmailService{
    private final EmailConfig mailConfig;

     @Override
    public SendEmailResponse sendMail(SendEmailRequest emailRequest) {
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