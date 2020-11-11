package me.vrnsky.server.service;

import me.vrnsky.server.domain.User;
import me.vrnsky.server.exception.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void whenRegisterOrUpdateUserShouldCheckThatWasAdded() {
        User user = new User("admin", "admin");
        userService.register(user);
        User registered = userService.getById(user.getId());
        assertThat(registered.getEmail(), is("admin"));
    }

    @Test
    public void whenTryUpdateUserShouldCheckThatUserWasUpdated() {
        User user = new User("admin", "admin");
        userService.register(user);
        user.setPassword("root");
        userService.register(user);
        User updatedUser = userService.getById(user.getId());
        assertThat(updatedUser.getPassword(), is("root"));
    }

    @Test
    public void whenTryGetExistUserByCreditsShouldCheckThatItIs() {
        User user = new User("admin", "admin");
        userService.register(user);
        User actual = userService.findByCredits("admin", "admin");
        assertThat(actual, is(user));
    }

    @Test(expected = UserNotFoundException.class)
    public void whenDeleteUserShouldCheckThatUserWasDeleted() {
        User user = new User("admin", "admin");
        userService.register(user);
        userService.deleteUser(user);
        User actual = userService.getById(user.getId());
    }
}