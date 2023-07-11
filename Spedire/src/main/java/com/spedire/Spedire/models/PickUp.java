package com.spedire.Spedire.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter@Setter@Document
public class PickUp {
    private Address currentLocation;

    private String senderName;

    private String phoneNumber;
}
