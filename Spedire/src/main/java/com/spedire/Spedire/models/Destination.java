package com.spedire.Spedire.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Destination {

    private Address receiverLocation;

    private String receiverName;

    private String receiverPhoneNumber;


}
