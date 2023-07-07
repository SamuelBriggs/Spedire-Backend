package com.spedire.Spedire.controllers;

import com.spedire.Spedire.OtpConfig.dtos.request.OtpVerificationRequest;
import com.spedire.Spedire.OtpConfig.exceptions.PhoneNumberNotVerifiedException;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.exceptions.UserAlreadyExistsException;
import com.spedire.Spedire.sms_sender.dtos.response.SendSmsResponse;
import com.spedire.Spedire.sms_sender.sms_service.SmsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class SmsController {
    private final SmsService smsService;

    @PostMapping("/verify-number")
    public ResponseEntity<SendSmsResponse> verifyPhoneNumber(@RequestParam String phoneNumber){
        try{
            SendSmsResponse response =smsService.sendSmsWithTwilio(phoneNumber);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (PhoneNumberNotVerifiedException | UserAlreadyExistsException exception){
            return ResponseEntity.badRequest().body( SendSmsResponse.builder().message(exception.getMessage()).build());
        } catch (SpedireException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/verify-otp")
    public ResponseEntity<SendSmsResponse> verifyOtp(@RequestParam OtpVerificationRequest request){
        try{
            SendSmsResponse response = smsService.verifyOtp(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body( SendSmsResponse.builder().message(e.getMessage()).build());
        }

    }
}