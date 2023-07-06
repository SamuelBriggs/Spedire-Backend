package com.spedire.Spedire.sms_sender.sms_service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.spedire.Spedire.Exception.SpedireException;
import com.spedire.Spedire.OtpConfig.dtos.request.OtpVerificationRequest;
import com.spedire.Spedire.OtpConfig.exceptions.PhoneNumberNotVerifiedException;
import com.spedire.Spedire.OtpConfig.services.OtpService;
import com.spedire.Spedire.security.JwtUtils;
import com.spedire.Spedire.sms_sender.config.TwilioConfig;
import com.spedire.Spedire.sms_sender.dtos.response.SendSmsResponse;
import com.spedire.Spedire.sms_sender.utils.AppUtils;
import com.spedire.Spedire.utils.JwtUtil;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.spedire.Spedire.OtpConfig.utils.ResponseUtils.OTP_VERIFIED_SUCCESSFULLY;
import static com.spedire.Spedire.sms_sender.utils.AppUtils.*;
import static com.spedire.Spedire.sms_sender.utils.ResponseUtils.*;
import static java.time.Instant.now;


@Service
@Slf4j
@AllArgsConstructor
public class SmsServiceImpl implements SmsService {
    private final TwilioConfig twilioConfig;
    private final JwtUtil jwtUtil;


    @Override
    public SendSmsResponse sendSmsWithTwilio(String phoneNumber) throws PhoneNumberNotVerifiedException {
        if (!validatePhoneNumber(phoneNumber)){
            throw new PhoneNumberNotVerifiedException(INVALID_PHONE_NUMBER);
        }
        String phone = phoneNumber.substring(1);
        log.info("Result of number substring "+phone);
            Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
           Verification verification = Verification.creator(
                   twilioConfig.getTwilioNumber(),
                    PHONE_NUMBER_PREFIX+phone,
                    "sms"
            ).create();
        log.info(verification.getStatus());
            if (verification.getStatus().equals(SMS_SENT_STATUS)) {
               String token= generateJwtToken(phoneNumber);

                return SendSmsResponse.builder().message(SMS_SENT_SUCCESS+ phoneNumber).success(true).data(token).build();
            } else {
                throw new PhoneNumberNotVerifiedException(SMS_SEND_FAILED + phoneNumber);
            }
        }


    @Override
    public SendSmsResponse verifyOtp(OtpVerificationRequest request) {
        VerificationCheck verification = VerificationCheck.creator(
                        twilioConfig.getTwilioNumber())
                .setTo(request.getPhoneNumber())
                .setCode(request.getOtpNumber())
                .create();

        System.out.println(verification.getStatus());
        if (verification.getStatus().equals(OTP_VALIDATION_STATUS)) {
            return SendSmsResponse.builder().message(OTP_VERIFIED_SUCCESSFULLY+ request.getPhoneNumber()).success(true).build();
        } else {

            return SendSmsResponse.builder().message(SMS_SEND_FAILED + request.getPhoneNumber()).success(false).build();
        }
    }
    private boolean validatePhoneNumber( String phoneNumber) {
        return AppUtils.isValidPhoneNumber(phoneNumber);

    }
    private String generateJwtToken(String phoneNumber) {
       return JWT.create().withIssuedAt(now()).
                withExpiresAt(now().plusSeconds(120000L)).
                withClaim("phoneNumber", phoneNumber).
                sign(Algorithm.HMAC512("samuel".getBytes()));
    }
    private static String validateToken(String token) throws SpedireException {
        Map<String, Claim> map = JwtUtils.extractClaimsFromToken(token);
        Claim phoneNumber = map.get("phoneNumber");
        return phoneNumber.toString();
    }
}
