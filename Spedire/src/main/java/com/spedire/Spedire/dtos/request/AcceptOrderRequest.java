package com.spedire.Spedire.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcceptOrderRequest {
    private String orderId;

    private String carrierId;
}
