package com.spedire.Spedire.dtos.response;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class FindUserResponse {
  private  String name;
  private String email;
  private String phoneNumber;
}
