package com.spedire.Spedire.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Getter
@Setter
public class Payment {
    @Id
    private String id;

    private Order orderId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private STATUS orderStatus;
}
