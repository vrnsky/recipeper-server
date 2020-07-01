package me.vrnsky.server.dto;

import lombok.Data;

@Data
public class UserCreationRequest {
    private String email;
    private String password;
}
