package me.vrnsky.server.controllers;

import me.vrnsky.server.domain.User;
import me.vrnsky.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService service) {
        this.userService = service;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public User registerUser(@ModelAttribute User user) {
        this.userService.registerOrUpdate(user);
        return this.userService.getById(user.getId());
    }


}
