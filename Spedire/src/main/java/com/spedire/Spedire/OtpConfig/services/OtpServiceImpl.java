package com.spedire.Spedire.OtpConfig.services;

import com.spedire.Spedire.OtpConfig.dtos.response.OtpResponse;
import com.spedire.Spedire.OtpConfig.exceptions.OtpException;
import com.spedire.Spedire.OtpConfig.exceptions.PhoneNumberNotVerifiedException;
import com.spedire.Spedire.OtpConfig.model.Otp;
import com.spedire.Spedire.OtpConfig.repository.OtpRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.spedire.Spedire.OtpConfig.utils.ResponseUtils.*;


@Service
@AllArgsConstructor
public class OtpServiceImpl implements OtpService {
    private final OtpRepository otpRepository;

    private int generateOtpNumber() {
        Random random = new Random();
        return 100000 + random.nextInt(500000);
    }
    @Override
    public OtpResponse createNewOtp(String phoneNumber){
        int otp = generateOtpNumber();
    otpRepository.save(Otp.builder().phoneNumber(phoneNumber).otpNumber(otp).build());
    return OtpResponse.builder().message(OTP_CREATED_SUCCESSFULLY+ phoneNumber).otpNumber(otp).build();
    }

    @Override
    public void deleteOtp(String phoneNumber) {
        otpRepository.deleteByPhoneNumber(phoneNumber);
        OtpResponse.builder()
                .message(OTP_DELETED_SUCCESSFULLY)
                .build();

    }
    public OtpResponse findByPhoneNumber(String phoneNumber) throws PhoneNumberNotVerifiedException {
       Otp response =otpRepository.findByPhoneNumber(phoneNumber);
       if(response == null) throw new PhoneNumberNotVerifiedException(PHONE_NUMBER_NOT_VERIFIED);
       return OtpResponse.builder().otpNumber(response.getOtpNumber()).build();
    }
}
