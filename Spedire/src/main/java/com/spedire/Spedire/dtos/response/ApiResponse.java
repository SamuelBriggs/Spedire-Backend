package com.spedire.Spedire.dtos.response;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class ApiResponse<T> {
    private String message;
    private boolean success;
    private Object data;
}
