package me.vrnsky.server.dto.authentication;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class AuthenticationRequest {
    @Size(min = 6, max = 20)
    private String username;
    @Size(min = 6, max = 30)
    private String password;
}
