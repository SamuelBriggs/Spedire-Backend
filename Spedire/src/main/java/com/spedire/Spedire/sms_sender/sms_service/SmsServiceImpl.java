package com.spedire.Spedire.sms_sender.sms_service;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;
import com.spedire.Spedire.OtpConfig.dtos.request.OtpVerificationRequest;
import com.spedire.Spedire.OtpConfig.dtos.response.OtpResponse;
import com.spedire.Spedire.OtpConfig.exceptions.OtpException;
import com.spedire.Spedire.OtpConfig.exceptions.PhoneNumberNotVerifiedException;
import com.spedire.Spedire.OtpConfig.services.OtpService;
import com.spedire.Spedire.sms_sender.config.TwilioConfig;
import com.spedire.Spedire.sms_sender.dtos.request.SmsNotificationRequest;
import com.spedire.Spedire.sms_sender.dtos.response.SendSmsResponse;
import com.spedire.Spedire.sms_sender.utils.AppUtils;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.spedire.Spedire.OtpConfig.utils.ResponseUtils.OTP_VERIFICATION_ERROR;
import static com.spedire.Spedire.OtpConfig.utils.ResponseUtils.OTP_VERIFIED_SUCCESSFULLY;
import static com.spedire.Spedire.sms_sender.utils.AppUtils.*;
import static com.spedire.Spedire.sms_sender.utils.ResponseUtils.SMS_SEND_FAILED;
import static com.spedire.Spedire.sms_sender.utils.ResponseUtils.SMS_SENT_SUCCESS;


@Service
@Slf4j
@AllArgsConstructor
public class SmsServiceImpl implements SmsService {
    private final NexmoClient nexmoClient;
    private final TwilioConfig twilioConfig;
    private final OtpService otpService;

    @Override
    public SendSmsResponse sendSms(String phoneNumber) throws IOException, NexmoClientException, PhoneNumberNotVerifiedException {
        //find user by phone number first
        if(!validatePhoneNumber(phoneNumber)){
            throw new PhoneNumberNotVerifiedException(PHONE_VALIDATION_FAILED_MESSAGE);
        }
        var otp=otpService.createNewOtp(phoneNumber);
        String message= OTP_VERIFICATION_MESSAGE + otp.getOtpNumber();
        SmsNotificationRequest request = new SmsNotificationRequest(phoneNumber,message);
        TextMessage sms = new TextMessage(YOUR_NEXMO_PHONE_NUMBER, request.getTo(), request.getMessage());
        SmsSubmissionResponse response = nexmoClient.getSmsClient().submitMessage(sms);
        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            log.info(SendSmsResponse.builder().message(otp.getMessage()).build().toString());
            return SendSmsResponse.builder().message(otp.getMessage()).build();
        } else {
            return SendSmsResponse.builder().message(SMS_SEND_FAILED  + response.getMessages().get(0).getErrorText() ).build();

        }
    }

    @Override
    public SendSmsResponse sendSmsWithTwilio(String phoneNumber) {
        String phone = phoneNumber.substring(1);
        log.info("Result of number substring "+phone);
            Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
            var otp=otpService.createNewOtp(phoneNumber);
            String message= OTP_VERIFICATION_MESSAGE + otp.getOtpNumber();
           Verification verification = Verification.creator(
                   twilioConfig.getTwilioNumber(),
                    PHONE_NUMBER_PREFIX+phone,
                    "sms"
            ).create();
        log.info(verification.getStatus());
            if (verification.getStatus().equals(SMS_SENT_STATUS)) {
                return SendSmsResponse.builder().message(SMS_SENT_SUCCESS+ phoneNumber).success(true).build();
            } else {

                return SendSmsResponse.builder().message(SMS_SEND_FAILED + phoneNumber).success(false).build();
            }
        }


    @Override
    public OtpResponse verifyOtp(OtpVerificationRequest request) throws OtpException, PhoneNumberNotVerifiedException {
        var response = otpService.findByPhoneNumber(request.getPhoneNumber());
        if (response.getOtpNumber() != request.getOtpNumber()) {
            throw new OtpException(OTP_VERIFICATION_ERROR);
        }
        return  OtpResponse.builder().message(OTP_VERIFIED_SUCCESSFULLY).build();
    }
    private boolean validatePhoneNumber( String phoneNumber) {
        return AppUtils.isValidPhoneNumber(phoneNumber);

    }
}
