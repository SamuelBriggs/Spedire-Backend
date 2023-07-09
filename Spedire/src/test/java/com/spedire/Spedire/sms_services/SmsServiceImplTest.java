package com.spedire.Spedire.sms_services;


import com.spedire.Spedire.OtpConfig.dtos.request.OtpVerificationRequest;
import com.spedire.Spedire.OtpConfig.exceptions.PhoneNumberNotVerifiedException;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.services.UserService;
import com.spedire.Spedire.sms_sender.dtos.request.SmsNotificationRequest;
import com.spedire.Spedire.sms_sender.sms_service.SmsService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    var response = smsService.sendSmsWithTwilio("07069310006");
    System.out.println(response.toString());
    assertNotNull(response);
}
@SneakyThrows
@Test
public void verifyOtpTest(){
    OtpVerificationRequest request = new OtpVerificationRequest();

    String otp ="502543";
    String token ="Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2ODg4NDkyMzYsImV4cCI6MTY4ODk2OTIzNiwicGhvbmVOdW1iZXIiOiIwNzA2OTMxMDAwNiIsIlJvbGVzIjp7InJvbGUiOiJBRE1JTiJ9fQ.s5zHM924B5wqtrl2m9HoOGHEIVWDDm1mzk4rsv6W5YoK-U_yjeFJh0d6EeQAvWkPScUJQGcqWrAAwXEUiR0s0Q";
    var response = smsService.verifyOtp(token, otp);
    assertNotNull(response);

}
@Test
    public void testFindUserByPhone() throws SpedireException {
    var found =userService.findUserByPhoneNumber("09051243133");
    assertTrue(found);
    }

}
