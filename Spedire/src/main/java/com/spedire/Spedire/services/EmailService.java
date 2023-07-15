package com.spedire.Spedire.services;

import com.spedire.Spedire.dtos.request.SendEmailRequest;
import com.spedire.Spedire.dtos.response.SendEmailResponse;


public interface EmailService {
    SendEmailResponse sendMail(SendEmailRequest request);
}