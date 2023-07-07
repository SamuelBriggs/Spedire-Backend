package com.spedire.Spedire.sms_services;


import com.nexmo.client.NexmoClientException;
import com.spedire.Spedire.OtpConfig.dtos.request.OtpVerificationRequest;
import com.spedire.Spedire.OtpConfig.exceptions.PhoneNumberNotVerifiedException;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.exceptions.UserAlreadyExistsException;
import com.spedire.Spedire.services.UserService;
import com.spedire.Spedire.sms_sender.dtos.request.SmsNotificationRequest;
import com.spedire.Spedire.sms_sender.sms_service.SmsService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SmsServiceImplTest {
@Autowired
    private SmsService smsService;
@Autowired
private UserService userService;

@Test
    public void sendSmsTest() throws PhoneNumberNotVerifiedException, SpedireException {
    SmsNotificationRequest request = new SmsNotificationRequest("08138732503", "Hello world");
    var response = smsService.sendSmsWithTwilio(request.getTo());
    System.out.println(response.toString());
    assertNotNull(response);

}
//@Test
//public void testTwilioSms(){
//    var response =smsService.sendSmsWithTwilio("+2348138732503");
//    assertNotNull(response);
//}
@SneakyThrows
@Test
public void verifyOtpTest(){
    OtpVerificationRequest request = new OtpVerificationRequest();

    request.setOtpNumber("949239");
    request.setToken("eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2ODg2ODI4NTQsImV4cCI6MTY4ODgwMjg1NCwicGhvbmVOdW1iZXIiOiIwODEzODczMjUwMyJ9.NCWg_MsLsaVn3Rgsqk-Tp34tQ9f0uvud3EuKxckFZZeX9pDRoviiaUDwkjIfppU9Eb-f4vAMYm9ee2LaSoARyA");
    var response = smsService.verifyOtp(request);
    assertNotNull(response);

}
@Test
    public void testFindUserByPhone() throws SpedireException {
    var found =userService.findUserByPhoneNumber("08138732503");
    assertTrue(found);
    }

}
