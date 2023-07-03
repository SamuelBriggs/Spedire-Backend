package com.spedire.Spedire.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Request {
    @Id
    private String id;
    private String destination;
    @Field("location")
    private Location coordinates;

}
