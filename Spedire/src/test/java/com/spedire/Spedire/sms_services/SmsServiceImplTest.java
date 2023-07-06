package com.spedire.Spedire.sms_services;


import com.nexmo.client.NexmoClientException;
import com.spedire.Spedire.OtpConfig.dtos.request.OtpVerificationRequest;
import com.spedire.Spedire.OtpConfig.exceptions.PhoneNumberNotVerifiedException;
import com.spedire.Spedire.sms_sender.dtos.request.SmsNotificationRequest;
import com.spedire.Spedire.sms_sender.sms_service.SmsService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SmsServiceImplTest {
@Autowired
    private SmsService smsService;

@Test
    public void sendSmsTest() throws PhoneNumberNotVerifiedException {
    SmsNotificationRequest request = new SmsNotificationRequest("+2349051243133", "Hello world");
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

    request.setPhoneNumber("+2348138732503");
    var response = smsService.verifyOtp(request);
    assertNotNull(response);

}

}
