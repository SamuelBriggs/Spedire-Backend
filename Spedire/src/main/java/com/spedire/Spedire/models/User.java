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
<<<<<<< HEAD
@ToString
=======
@RequiredArgsConstructor
@AllArgsConstructor
>>>>>>> 49bf41d9b11a6c286851b8885a4c2a87c307b6ab
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
    private Set<Role> roles;
    private LocalDateTime createdAt;
    private Set<Role> roles;
}
