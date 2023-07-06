package com.spedire.Spedire.sms_sender.sms_service;

import com.nexmo.client.NexmoClientException;
import com.spedire.Spedire.OtpConfig.dtos.request.OtpVerificationRequest;
import com.spedire.Spedire.OtpConfig.dtos.response.OtpResponse;
import com.spedire.Spedire.OtpConfig.exceptions.OtpException;
import com.spedire.Spedire.OtpConfig.exceptions.PhoneNumberNotVerifiedException;
import com.spedire.Spedire.sms_sender.dtos.request.SmsNotificationRequest;
import com.spedire.Spedire.sms_sender.dtos.response.SendSmsResponse;

import java.io.IOException;

public interface SmsService {
    SendSmsResponse sendSms(String phoneNumber) throws IOException, NexmoClientException, PhoneNumberNotVerifiedException;
    SendSmsResponse sendSmsWithTwilio(String phoneNumber);
    OtpResponse verifyOtp(OtpVerificationRequest request) throws OtpException, PhoneNumberNotVerifiedException;

}
