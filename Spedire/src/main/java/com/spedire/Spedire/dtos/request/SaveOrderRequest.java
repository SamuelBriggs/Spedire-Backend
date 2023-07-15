package com.spedire.Spedire.dtos.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SaveOrderRequest {
    private String senderName;
    private String phoneNumber;
    private String pickUpCity;
    private String pickHouseNumber;
    private String pickBusStop;
    private String pickUpStreetName;
    private String pickUpLandmark;


    private String receiverName;
    private String receiverPhoneNumber;
    private String receiverDestination;
    private String receiverHouseNumber;
    private String receiverBusStop;
    private String receiverStreetName;
    private String receiverLandmark;


    private String dueTime;
    private String dueDate;
    private String weight;
    private String description;
    private String image;
    private String itemType;
    private String costOfItem;

}
