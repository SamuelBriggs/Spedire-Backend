package com.spedire.Spedire.dtos.response;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private boolean success;
    private String data;
}
