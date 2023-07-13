package com.spedire.Spedire.OtpConfig.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Otp {
    @Id
    private String id;
    private String otpNumber;
    private String phoneNumber;
    private LocalDateTime createdAt;

private void setCreatedAt(){
    createdAt= LocalDateTime.now();
}
}
