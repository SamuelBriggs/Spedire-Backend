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
    SmsNotificationRequest request = new SmsNotificationRequest("09051243133", "Hello world");
    var response = smsService.sendSmsWithTwilio(request.getTo());
    System.out.println(response.toString());
    assertNotNull(response);
}
@SneakyThrows
@Test
public void verifyOtpTest(){
    OtpVerificationRequest request = new OtpVerificationRequest();

    request.setOtpNumber("792660");
    request.setToken("eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2ODg3MzMzOTEsImV4cCI6MTY4ODg1MzM5MSwicGhvbmVOdW1iZXIiOiIwOTA1MTI0MzEzMyJ9.f_yii-ewt3jd_Cqmwb-OGbocTRPrfw-gqVuumjocKbRNmmBbZe-w62GPfmmI9swAlUkQpzeNOuzywlichZ1Ptw");
    var response = smsService.verifyOtp(request);
    assertNotNull(response);

}
@Test
    public void testFindUserByPhone() throws SpedireException {
    var found =userService.findUserByPhoneNumber("09051243133");
    assertTrue(found);
    }

}
