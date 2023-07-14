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
        return 100 + random.nextInt(500);
    }
    @Override
    public OtpResponse createNewOtp(String phoneNumber){
        String otp = String.valueOf(generateOtpNumber());
    var new_otp=otpRepository.save(Otp.builder().phoneNumber(phoneNumber).otpNumber(otp).build());
    if(new_otp.getId()==null){
    return OtpResponse.builder().message(OTP_CREATION_UNSUCCESSFUL).success(false).build();}
        return OtpResponse.builder().message(OTP_CREATED_SUCCESSFULLY+ phoneNumber).otpNumber(new_otp.getOtpNumber()).success(true).build();
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

    @Override
    public Otp findByOtp(String otpNumber) throws OtpException {
        Otp response =otpRepository.findByOtpNumber(otpNumber);
        if (response != null) {
            return response;
        }
        throw new OtpException(OTP_SEARCH_FAILED);
    }
}
