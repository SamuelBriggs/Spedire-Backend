package com.spedire.Spedire.sms_sender.sms_service;

import com.nexmo.client.NexmoClientException;
import com.spedire.Spedire.Exception.SpedireException;
import com.spedire.Spedire.OtpConfig.dtos.request.OtpVerificationRequest;
import com.spedire.Spedire.OtpConfig.dtos.response.OtpResponse;
import com.spedire.Spedire.OtpConfig.exceptions.OtpException;
import com.spedire.Spedire.OtpConfig.exceptions.PhoneNumberNotVerifiedException;
import com.spedire.Spedire.exceptions.UserAlreadyExistsException;
import com.spedire.Spedire.sms_sender.dtos.request.SmsNotificationRequest;
import com.spedire.Spedire.sms_sender.dtos.response.SendSmsResponse;

import java.io.IOException;

public interface SmsService {
    SendSmsResponse sendSmsWithTwilio(String phoneNumber) throws PhoneNumberNotVerifiedException, com.spedire.Spedire.exceptions.SpedireException;
    SendSmsResponse verifyOtp(OtpVerificationRequest request) throws PhoneNumberNotVerifiedException, SpedireException, UserAlreadyExistsException;

}
