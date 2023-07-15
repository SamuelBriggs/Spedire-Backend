package com.spedire.Spedire.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Location {
    private double latitude;
    private double longitude;
}
