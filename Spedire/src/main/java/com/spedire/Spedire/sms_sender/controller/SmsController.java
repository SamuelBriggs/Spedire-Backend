package com.spedire.Spedire.sms_sender.controller;

import com.spedire.Spedire.OtpConfig.dtos.request.OtpVerificationRequest;
import com.spedire.Spedire.OtpConfig.exceptions.PhoneNumberNotVerifiedException;
import com.spedire.Spedire.sms_sender.dtos.response.SendSmsResponse;
import com.spedire.Spedire.sms_sender.sms_service.SmsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class SmsController {
    private final SmsService smsService;

    @PostMapping("/verify-number")
    public ResponseEntity<SendSmsResponse> verifyPhoneNumber(@RequestBody String phoneNumber){
        try{
            SendSmsResponse response =smsService.sendSmsWithTwilio(phoneNumber);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (PhoneNumberNotVerifiedException exception){
            return ResponseEntity.badRequest().body( SendSmsResponse.builder().message(exception.getMessage()).build());
        }
    }
    @PostMapping("/verify-otp")
    public ResponseEntity<SendSmsResponse> verifyOtp(@RequestBody OtpVerificationRequest request){
        try{
            SendSmsResponse response = smsService.verifyOtp(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (PhoneNumberNotVerifiedException e) {
            return ResponseEntity.badRequest().body( SendSmsResponse.builder().message(e.getMessage()).build());
        }

    }
}
