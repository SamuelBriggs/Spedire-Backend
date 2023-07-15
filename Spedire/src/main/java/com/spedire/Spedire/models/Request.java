package com.spedire.Spedire.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document@Getter@Setter
@Builder@RequiredArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    private String id;
    private Address destination;

    private Address currentLocation;

    private String phoneNumber;

    private LocalDateTime createdAt;


}
