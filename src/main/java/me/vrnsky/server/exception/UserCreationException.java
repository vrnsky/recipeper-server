package me.vrnsky.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "You try get not exist user")
public class UserCreationException extends RuntimeException {
    public UserCreationException(String errorMessage) {
        super(errorMessage);
    }
}
