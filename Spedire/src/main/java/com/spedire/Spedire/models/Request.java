package com.spedire.Spedire.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Request {
    @Id
    private String id;
    private String destination;
    private String currentLocation;

}
