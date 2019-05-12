package me.vrnsky.server.dto;

import lombok.Data;

@Data
public class ApiResponse {
    private ResponseStatus status;
    private String message;
}
