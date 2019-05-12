package me.vrnsky.server.service;

import me.vrnsky.server.domain.User;
import me.vrnsky.server.exception.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @MockBean
    private EmailService emailService;

    @Before
    public void setUp() {
        doNothing()
                .when(emailService).send(anyString(), anyString(), anyString());
    }

    @Test
    public void whenRegisterOrUpdateUserShouldCheckThatWasAdded() {
        User user = new User("admin@recipeper.com", "vrrnsky", "admin");
        userService.register(user);
        User registered = userService.getById(user.getId());
        assertThat(registered.getEmail(), is("admin@recipeper.com"));
        verify(emailService).send(user.getEmail(), "Welcome", "To the Recipeper");
    }

    @Test
    public void whenTryUpdateUserShouldCheckThatUserWasUpdated() {
        User user = new User("admin", "vrrnsky", "admin");
        userService.register(user);
        user.setPassword("root");
        userService.register(user);
        User updatedUser = userService.getById(user.getId());
        assertThat(updatedUser.getEmail(), is("admin"));
    }

    @Test
    public void whenTryGetExistUserByCreditsShouldCheckThatItIs() {
        User user = new User("admin", "vrrnsky", "admin");
        userService.register(user);
        User actual = userService.findByUsername("vrrnsky");
        assertThat(actual, is(user));
    }

    @Test(expected = UserNotFoundException.class)
    public void whenDeleteUserShouldCheckThatUserWasDeleted() {
        User user = new User("admin", "vrrnsky", "admin");
        userService.register(user);
        userService.deleteUser(user);
        User actual = userService.getById(user.getId());
    }
}