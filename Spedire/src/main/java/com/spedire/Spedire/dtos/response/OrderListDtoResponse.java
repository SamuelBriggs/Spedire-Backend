package com.spedire.Spedire.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class OrderListDtoResponse {

    private String orderId;
    private String senderId;
    private String recieverId;
    private String orderType;
    private String currentLocationStreetName;
    private String senderName;
    private String senderPhoneNumber;
    private String destinationStreetName;
    private String destinationLandmark;

}
