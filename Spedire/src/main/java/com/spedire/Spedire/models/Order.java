package com.spedire.Spedire.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
@Getter
@Setter
public class Order {
    @Id
    private String id;
    private String senderId;
    private String carrierId;
    private String paymentId;
    private Address destination;
    private ItemType type;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime deliveredAt;
    private LocalDateTime eta;
    private BigDecimal costOfItem;
    private BigDecimal costOfDelivery;
    private String image;
}
