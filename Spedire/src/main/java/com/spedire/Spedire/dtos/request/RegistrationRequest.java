package com.spedire.Spedire.dtos.request;

import com.spedire.Spedire.models.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
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

}