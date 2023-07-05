package com.spedire.Spedire.OtpConfig.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Otp {
    @Id
    private String id;
    private int otpNumber;
    private String phoneNumber;


}
