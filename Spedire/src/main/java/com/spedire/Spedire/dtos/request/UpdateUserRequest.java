package com.spedire.Spedire.dtos.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUserRequest {
    @Email
    private String email;

    @Size(min = 6)
    @Pattern.List({
            @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one digit."),
            @Pattern(regexp = ".*[a-z].*", message = "Password must contain at least one lowercase letter."),
            @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter."),
            @Pattern(regexp = ".*[!@#$%^&*()].*", message = "Password must contain at least one special character.")
    })
    private String password;

    @NotBlank(message = "First name is required.")
    @Size(min = 2, message = "First name should have at least 2 letters.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @Size(min = 2, message = "Last name should have at least 2 letters.")
    private String lastName;
    private String phoneNumber;
    private String bankId;
    private String accountName;
    private String accountNumber;
    private MultipartFile profileImage;
}