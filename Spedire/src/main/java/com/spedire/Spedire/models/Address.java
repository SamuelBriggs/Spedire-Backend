package com.spedire.Spedire.models;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Address {
    @Id
    private String id;
    private String streetName;
    private String streetNumber;
    private String landMark;
    private String state;
    private String city;
}
