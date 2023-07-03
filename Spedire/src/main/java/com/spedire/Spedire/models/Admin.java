package com.spedire.Spedire.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Admin {
    @Id
    private String id;

    private String firstName;

    private String lastName;
    @Indexed(unique = true)
    private String email;

    private String password;
    private LocalDateTime createdAt;
    private Role role;


}
