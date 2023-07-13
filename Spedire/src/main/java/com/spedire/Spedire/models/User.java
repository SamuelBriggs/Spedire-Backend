package com.spedire.Spedire.models;


import com.spedire.Spedire.dtos.response.MatchedUserDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
    @Indexed(unique = true)
    private String phoneNumber;
    private String profileImage;
//    @Indexed(unique = true)
    private String email;
    @DBRef
    @Field("bank_id")
    private String bankId;
    @DBRef
    @Field("address_id")
    private Address addressId;
    private Set<Role> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private MatchedUserDto matchedUserDTO;
    private String bvn;
    private String guarantorName;
    private String guarantorPhoneNumber;

}
