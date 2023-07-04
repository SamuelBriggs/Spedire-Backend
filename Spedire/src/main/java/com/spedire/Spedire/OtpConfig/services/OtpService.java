package com.spedire.Spedire.OtpConfig.services;

import com.spedire.Spedire.OtpConfig.dtos.response.OtpResponse;
import com.spedire.Spedire.OtpConfig.exceptions.OtpException;
import com.spedire.Spedire.OtpConfig.exceptions.PhoneNumberNotVerifiedException;

public interface OtpService {
    OtpResponse createNewOtp(String phoneNumber);
    void deleteOtp(String phoneNumber);
    OtpResponse findByPhoneNumber(String phoneNumber) throws PhoneNumberNotVerifiedException;
}
