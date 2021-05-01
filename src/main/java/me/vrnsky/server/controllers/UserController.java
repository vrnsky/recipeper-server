package me.vrnsky.server.controllers;

import lombok.RequiredArgsConstructor;
import me.vrnsky.server.domain.User;
import me.vrnsky.server.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/register")
    public User registerUser(@RequestBody User user) {
        this.userService.register(user);
        return this.userService.getById(user.getId());
    }
}
