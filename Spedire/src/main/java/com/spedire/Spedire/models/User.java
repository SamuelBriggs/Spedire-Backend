package com.spedire.Spedire.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Set;

@Document
@Builder
@Getter
@Setter

@ToString

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
    private Bank bankId;
    @DBRef
    @Field("address_id")
    private Address addressId;
<<<<<<< HEAD
    private Set<Role> roles;
    private LocalDateTime createdAt;

=======
    @DBRef
    private Role USER;
    private LocalDateTime createdAt;
    private Set<Role> roles;
    private Boolean isConfirmed = false;
    private Boolean isVerified = false;
>>>>>>> e10467bc7d8031ee3cfb02133d2fff3769db5c0d
}
