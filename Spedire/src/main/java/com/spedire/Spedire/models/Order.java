package com.spedire.Spedire.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Document
@Getter
@Setter
@ToString

public class Order {

    @Id
    private String id;
    private String senderId;
    private String senderName;
    private String receiverName;
    private String carrierId;
    private Payment paymentId;
    private PickUp pickUpLocation;
    private Reciever receiverDestination;
    private ItemType type;
    private String image;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime deliveredAt;
    private Date dueDate;
    private LocalTime dueTime;
    private LocalTime weight;
    private LocalDateTime eta;
    private BigDecimal costOfItem;
    private BigDecimal costOfDelivery;
    private boolean isAccepted;
    private boolean isCompleted;



}
