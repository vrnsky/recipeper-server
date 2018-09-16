package me.vrnsky.server.controllers;

import me.vrnsky.server.domain.User;
import me.vrnsky.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService service) {
        this.userService = service;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User registerUser(@ModelAttribute User user) {
        this.userService.registerOrUpdate(user);
        return this.userService.getById(user.getId());
    }


}
