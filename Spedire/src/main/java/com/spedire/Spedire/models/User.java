package com.spedire.Spedire.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.Set;

@Document
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String password;
//    @Indexed(unique = true)
    private String phoneNumber;
    private String profileImage;
    @Indexed(unique = true)
    private String email;
    @DBRef
    @Field("bank_id")
    private String bankId;
    @DBRef
    @Field("address_id")
    private Address addressId;
    private Role USER;
    private LocalDateTime createdAt;
    private Set<Role> roles;
}
