package com.spedire.Spedire.sms_sender.sms_service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.spedire.Spedire.Exception.SpedireException;
import com.spedire.Spedire.OtpConfig.dtos.response.OtpResponse;
import com.spedire.Spedire.OtpConfig.exceptions.OtpException;
import com.spedire.Spedire.OtpConfig.exceptions.PhoneNumberNotVerifiedException;
import com.spedire.Spedire.OtpConfig.services.OtpService;
import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.exceptions.UserAlreadyExistsException;
import com.spedire.Spedire.models.Role;
import com.spedire.Spedire.security.JwtUtils;
import com.spedire.Spedire.services.UserService;
import com.spedire.Spedire.sms_sender.config.TwilioConfig;
import com.spedire.Spedire.sms_sender.dtos.response.SendSmsResponse;
import com.spedire.Spedire.sms_sender.utils.AppUtils;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.spedire.Spedire.OtpConfig.utils.ResponseUtils.OTP_VERIFICATION_ERROR;
import static com.spedire.Spedire.OtpConfig.utils.ResponseUtils.OTP_VERIFIED_SUCCESSFULLY;
import static com.spedire.Spedire.sms_sender.utils.AppUtils.*;
import static com.spedire.Spedire.sms_sender.utils.ResponseUtils.*;
import static com.spedire.Spedire.utils.Constants.USER;
import static com.spedire.Spedire.utils.Constants.ZERO_STRING;
import static com.spedire.Spedire.utils.ResponseUtils.USER_ALREADY_EXIST;
import static java.time.Instant.now;


@Service
@Slf4j
@AllArgsConstructor
public class SmsServiceImpl implements SmsService {
    private final TwilioConfig twilioConfig;
    private final UserService userService;
    private final OtpService otpService;

    private final JwtUtils jwtUtils;



    @Override
    public SendSmsResponse sendSms(String phoneNumber) throws PhoneNumberNotVerifiedException, com.spedire.Spedire.exceptions.SpedireException {
        if (!validatePhoneNumber(phoneNumber)){
            throw new PhoneNumberNotVerifiedException(INVALID_PHONE_NUMBER);
        }
        var found=userService.findUserByPhoneNumber(phoneNumber);

        if(found){ throw new UserAlreadyExistsException(USER_ALREADY_EXIST);}
        OtpResponse response = otpService.createNewOtp(phoneNumber);

            if (response.isSuccess()) {
               String token= generateJwtToken(phoneNumber);
                return SendSmsResponse.builder().message(String.format(SMS_SENT_SUCCESS+ phoneNumber) +"   "+ SMS_MESSAGE+ response.getOtpNumber()).success(true).data(token).build();
            } else {
                throw new PhoneNumberNotVerifiedException(SMS_SEND_FAILED + phoneNumber);
            }
        }

    @Override
    public SendSmsResponse sendSmsWithTwilio(String phoneNumber) throws PhoneNumberNotVerifiedException, com.spedire.Spedire.exceptions.SpedireException {
        if (!validatePhoneNumber(phoneNumber)){
            throw new PhoneNumberNotVerifiedException(INVALID_PHONE_NUMBER);
        }
        var found=userService.findUserByPhoneNumber(phoneNumber);

        if(found){ throw new UserAlreadyExistsException(USER_ALREADY_EXIST);}
        String phone = phoneNumber.substring(1);
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
    public SendSmsResponse verifyOtp(String aToken, String otp) throws SpedireException, PhoneNumberNotVerifiedException {
       String token = aToken.split(" ")[1];
        String phoneNumber = validateToken(token);
        String phone = phoneNumber.substring(2,12);
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
        VerificationCheck verification = VerificationCheck.creator(
                        twilioConfig.getTwilioNumber())
                .setTo(PHONE_NUMBER_PREFIX+phone)
                .setCode(otp)
                .create();
                if (verification.getStatus().equals(OTP_VALIDATION_STATUS)) {
           ApiResponse<?> newUser= userService.saveNewUser(ZERO_STRING+phone);
            return SendSmsResponse.builder().message(OTP_VERIFIED_SUCCESSFULLY).success(true).build();

        } else {
            throw new PhoneNumberNotVerifiedException(String.format(OTP_VERIFICATION_ERROR, phoneNumber));

        }
    }
    @Override
    public SendSmsResponse verifySmsOtp(String aToken, String otp) throws SpedireException, PhoneNumberNotVerifiedException, OtpException {
        String token = aToken.split(" ")[1];
        log.info("print the token getting into here " + token);
        String phoneNumber = validateToken(token);
        String phone = phoneNumber.substring(1, 12);
        var found = otpService.findByOtp(otp);
        if (found.getPhoneNumber().equals(phone)) {
            ApiResponse<?> newUser = userService.saveNewUser(phone);
            otpService.deleteOtp(phone);
            return SendSmsResponse.builder().message(OTP_VERIFIED_SUCCESSFULLY).success(true).build();
        }
        throw new PhoneNumberNotVerifiedException(String.format(OTP_VERIFICATION_ERROR, phoneNumber));
    }


    @Override
    public SendSmsResponse resendOtp(String aToken) throws com.spedire.Spedire.exceptions.SpedireException, PhoneNumberNotVerifiedException, SpedireException {
        String token = aToken.split(" ")[1];
        String phoneNumber = validateToken(token);
        String phone = phoneNumber.substring(1,12);
        var found=userService.findUserByPhoneNumber(phone);

        if(found){ throw new UserAlreadyExistsException(USER_ALREADY_EXIST);}
        OtpResponse response = otpService.createNewOtp(phone);

        if (response.isSuccess()) {
            return SendSmsResponse.builder().message(String.format(SMS_SENT_SUCCESS+ phone) +"   "+ SMS_MESSAGE+ response.getOtpNumber()).success(true).data(token).build();
        } else {
            throw new PhoneNumberNotVerifiedException(SMS_SEND_FAILED + phoneNumber);
        }
    }

    private boolean validatePhoneNumber( String phoneNumber) {
        return AppUtils.isValidPhoneNumber(phoneNumber);

    }
    private String generateJwtToken(String phoneNumber) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("role", Role.NEW_USER.name());

       return JWT.create().withIssuedAt(now()).
                withExpiresAt(now().plusSeconds(120000L)).
                withClaim("phoneNumber", phoneNumber).
                withClaim("Roles", map).
                sign(Algorithm.HMAC512(jwtUtils.getSecret().getBytes()));

    }
    private  String validateToken(String token) throws SpedireException {
        log.info("is is getting into here");

        Map<String, Claim> map = jwtUtils.extractClaimsFromToken(token);
        log.info("this is map" + map);
        Claim phoneNumber = map.get("phoneNumber");
        return phoneNumber.toString();
    }
}
