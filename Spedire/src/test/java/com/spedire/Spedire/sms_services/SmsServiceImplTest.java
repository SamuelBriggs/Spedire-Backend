//package com.spedire.Spedire.sms_services;
//
//
//import com.spedire.Spedire.OtpConfig.dtos.request.OtpVerificationRequest;
//import com.spedire.Spedire.OtpConfig.exceptions.OtpException;
//import com.spedire.Spedire.OtpConfig.exceptions.PhoneNumberNotVerifiedException;
//import com.spedire.Spedire.OtpConfig.repository.OtpRepository;
//import com.spedire.Spedire.OtpConfig.services.OtpService;
//import com.spedire.Spedire.exceptions.SpedireException;
//import com.spedire.Spedire.services.UserService;
//import com.spedire.Spedire.sms_sender.dtos.request.sms_service.SmsService;
//import lombok.SneakyThrows;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//<<<<<<< HEAD
//=======
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//>>>>>>> ec4de2c98445776248af365f1a3a381f4cbdcf7d
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//public class SmsServiceImplTest {
//@Autowired
//    private SmsService smsService;
//@Autowired
//private UserService userService;
//@Autowired
//private OtpRepository otpRepository;
//    @Autowired
//    private OtpService otpService;
//
//@Test
//    public void sendSmsTest() throws PhoneNumberNotVerifiedException, SpedireException {
//    var response = smsService.sendSms("09051243133");
//    System.out.println(response.toString());
//    assertNotNull(response);
//}
//@SneakyThrows
//@Test
//public void verifyOtpTest(){
//
//    OtpVerificationRequest request = new OtpVerificationRequest();
//    String otp ="187";
//    String token ="Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2ODkyNTQ4MTAsImV4cCI6MTY4OTM3NDgxMCwicGhvbmVOdW1iZXIiOiIwOTA1MTI0MzEzMyIsIlJvbGVzIjp7InJvbGUiOiJ1c2VyIn19.1UCFcRtqX1yFYpwP0nVQPXElWkQeqqdYfeQTePQO0nvOPoiobqOlZiiLKrxZaWlvrbwflC9rigyIBICmkl2V0w";
//    var response = smsService.verifySmsOtp(token, otp);
//
//    assertNotNull(response);
//
//}
//@Test
//    public void testFindUserByPhone() throws SpedireException {
//    var found =userService.findUserByPhoneNumber("09051243133");
//    assertTrue(found);
//    }
//    @Test
//    public void testOtpByOtpNumber() throws SpedireException {
//        var found =otpRepository.findByOtpNumber("187");
//        System.out.println(found);
//
//    }
//    @Test
//    public void testGetOtpByOtpNumber() throws SpedireException, OtpException {
//        var found =otpService.findByOtp("187");
//        System.out.println(found);
//
//    }
//}
