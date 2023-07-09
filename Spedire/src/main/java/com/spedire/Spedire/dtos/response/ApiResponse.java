package com.spedire.Spedire.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
<<<<<<< HEAD
public class ApiResponse<T> {
    private String message;
    private boolean success;
    private Object data;
}
=======
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private String message;
    private boolean success;
    private T data;
}
>>>>>>> 506e99c5c2e601512af8cf6dcd85c62f84b85b57
