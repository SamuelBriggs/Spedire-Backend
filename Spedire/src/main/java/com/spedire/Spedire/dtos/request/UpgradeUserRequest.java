package com.spedire.Spedire.dtos.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpgradeUserRequest {

    private String bvn;

    private String guarantorName;

    private String guarantorPhoneNumber;

    private String imageUrl;

    private String userId;

}
