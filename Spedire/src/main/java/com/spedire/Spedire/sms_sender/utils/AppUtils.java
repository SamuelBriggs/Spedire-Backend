package com.spedire.Spedire.sms_sender.utils;

import java.util.regex.Pattern;

public class AppUtils {
    private static final String NIGERIAN_PHONE_REGEX = "((^090)([23589]))|((^070)([1-9]))|((^080)([2-9]))|((^081)([0-9]))(\\d{7})";
    private static final Pattern pattern = Pattern.compile(NIGERIAN_PHONE_REGEX);
    public static final String PHONE_VALIDATION_FAILED_MESSAGE= "Your Spedire verification code is: ";
    public static final String TWILO_ACCOUNT_SID="${twilio.accountSid}";
    public static final String TWILO_AUTH_TOKEN="${twilio.authToken}";
    public static final String TWILO_NUMBER="${twilio.number}";
    public static final String VONAGE_API_KEYS="${nexmo.creds.api-key}";
    public static final String VONAGE_API_SECRETS="${nexmo.creds.secret}";
    public static final String PHONE_NUMBER_PREFIX="+234";
    public static final String SMS_SENT_STATUS="pending";
    public static final String OTP_VALIDATION_STATUS="approved";
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return pattern.matcher(phoneNumber).matches();
    }


}
