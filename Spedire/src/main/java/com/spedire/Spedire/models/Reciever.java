package com.spedire.Spedire.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter@Setter
@Document
@ToString
public class Reciever {

    @Id
    private String id;
    private Address receiverDestination;
    private String receiverName;
    private String receiverBusStop;
    private String receiverPhoneNumber;
    private String receiverStreetName;
    private String receiverHouseNumber;
    private String receiverLandmark;


}
