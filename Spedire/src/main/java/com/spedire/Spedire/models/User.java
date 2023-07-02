package com.spedire.Spedire.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class User {
    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String password;

    private String profileImage;

    private String email;

    private String bankId;

    private String addressId;

}
