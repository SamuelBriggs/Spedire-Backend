package com.spedire.Spedire.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter@Setter
@Document
@ToString
public class Destination {

    @Id
    private String id;
    private Address receiverLocation;

    private String receiverName;

    private String receiverPhoneNumber;

}
