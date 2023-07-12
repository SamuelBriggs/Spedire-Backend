package com.spedire.Spedire.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter@Setter@Document
@ToString
public class PickUp {
    @Id
    private String id;

    private Address currentLocation;

    private String senderName;

    private String phoneNumber;
}
