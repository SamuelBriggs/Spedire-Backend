package com.spedire.Spedire.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Bank {
    @Id
    private String id;

    private String bankName;

    private String bankAccount;



}
