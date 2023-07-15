package com.spedire.Spedire.models;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    private String id;
    private String streetName;
    private String streetNumber;
    private String landMark;
    private String state;
    private String city;

}
