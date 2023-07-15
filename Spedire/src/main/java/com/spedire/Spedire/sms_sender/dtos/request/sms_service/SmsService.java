package com.spedire.Spedire.sms_sender.dtos.request.sms_service;

import com.nexmo.client.NexmoClientException;
import com.spedire.Spedire.OtpConfig.dtos.request.OtpVerificationRequest;
import com.spedire.Spedire.OtpConfig.dtos.response.OtpResponse;
import com.spedire.Spedire.OtpConfig.exceptions.OtpException;
import com.spedire.Spedire.OtpConfig.exceptions.PhoneNumberNotVerifiedException;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.exceptions.UserAlreadyExistsException;
import com.spedire.Spedire.sms_sender.dtos.request.SmsNotificationRequest;
import com.spedire.Spedire.sms_sender.dtos.response.SendSmsResponse;

import java.io.IOException;

public interface SmsService {
    SendSmsResponse sendSmsWithTwilio(String phoneNumber) throws PhoneNumberNotVerifiedException, com.spedire.Spedire.exceptions.SpedireException;
    SendSmsResponse verifyOtp(String token, String otp) throws PhoneNumberNotVerifiedException, SpedireException, UserAlreadyExistsException;

    SendSmsResponse sendSms(String phoneNumber) throws PhoneNumberNotVerifiedException, com.spedire.Spedire.exceptions.SpedireException;
    SendSmsResponse verifySmsOtp(String aToken, String otp) throws SpedireException, PhoneNumberNotVerifiedException, OtpException;

    SendSmsResponse resendOtp(String token) throws SpedireException, PhoneNumberNotVerifiedException, com.spedire.Spedire.exceptions.SpedireException;
}
