package com.spedire.Spedire.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter@Setter@Document
public class Destination {
    private Address receiverLocation;

    private String receiverName;

    private String receiverPhoneNumber;

}
