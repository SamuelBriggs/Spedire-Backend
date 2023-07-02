package com.spedire.Spedire.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Review {
    @Id
    private String id;

    private String reviewGiverId;

    private String recipientId;

    private LocalDateTime dateCreated;

}
