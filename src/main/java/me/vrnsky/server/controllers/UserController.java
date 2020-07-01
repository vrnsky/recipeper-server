package me.vrnsky.server.controllers;

import me.vrnsky.server.domain.User;
import me.vrnsky.server.dto.UserCreationRequest;
import me.vrnsky.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService service) {
        this.userService = service;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    //TODO refactor
    public User registerUser(@RequestBody UserCreationRequest userCreationRequest) {
        this.userService.registerOrUpdate(userCreationRequest);
        return null;
    }


}
