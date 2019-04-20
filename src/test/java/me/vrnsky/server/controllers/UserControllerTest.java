package me.vrnsky.server.controllers;

import me.vrnsky.server.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void whenTryRegisterUserShouldCheckThatUserWasRegistered() {
        User user = new User("mail", "password");
        User registeredUser = userController.registerUser(user);
        assertThat(registeredUser.getEmail(), is(user.getEmail()));
    }
}