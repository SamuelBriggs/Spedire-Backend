package com.spedire.Spedire.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
    private Payment paymentId;
    private Address destination;
    @Field("location")
    private Location coordinates;
    private ItemType type;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime deliveredAt;
    private LocalDateTime eta;
    private BigDecimal costOfItem;
    private BigDecimal costOfDelivery;
    private boolean isAccepted;
    private boolean isCompleted;

}
