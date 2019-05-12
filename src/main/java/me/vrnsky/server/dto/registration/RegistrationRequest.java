package me.vrnsky.server.dto.registration;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistrationRequest {
    @Email(message = "Email address must be valid")
    @NotNull
    @Size(max = 100, message = "Email address must contains less than 100 characters")
    private String email;
    @NotNull
    @Size(min = 6, max = 20, message = "Username must contains from 6 characters to 20 characters")
    private String username;
    @NotNull
    @Size(min = 6, max = 20, message = "Password must contains between 6 and 20 characters")
    private String password;
}
