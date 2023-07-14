package com.spedire.Spedire.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryRequestDTO {
    private String currentLocationCity;
    private String currentLocationBusStop;
    private String currentLocationLandmark;
    private String currentLocationStreetName;
    private String currentLocationHouseNumber;
    private String destinationCity;
    private String destinationBusStop;
    private String destinationLandmark;
    private String destinationStreetName;
    private String destinationHouseNumber;
}
