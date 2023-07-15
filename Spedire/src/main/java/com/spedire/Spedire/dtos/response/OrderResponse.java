package com.spedire.Spedire.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class OrderResponse {

    private String orderId;
    private String senderName;
    private String senderPhoneNumber;
    private String carrierName;
    private String carrierPhoneNumber;
    private String itemType;
    private String destination;
    private String location;

}
