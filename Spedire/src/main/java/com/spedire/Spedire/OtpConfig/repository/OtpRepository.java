package com.spedire.Spedire.OtpConfig.repository;

import com.spedire.Spedire.OtpConfig.dtos.response.OtpResponse;
import com.spedire.Spedire.OtpConfig.model.Otp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OtpRepository extends MongoRepository<Otp, String> {
    Otp findByPhoneNumber(String phoneNumber);

    void deleteByPhoneNumber(String phoneNumber);

    Otp findByOtpNumber(String otpNumber);
}
