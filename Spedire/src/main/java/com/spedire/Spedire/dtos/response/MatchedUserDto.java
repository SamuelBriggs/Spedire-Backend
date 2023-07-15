package com.spedire.Spedire.dtos.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MatchedUserDto {
    private String carrierName;
    private String carrierPhoneNumber;
}
