package com.spedire.Spedire.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Admin {
    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;


}
