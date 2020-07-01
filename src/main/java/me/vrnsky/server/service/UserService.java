package me.vrnsky.server.service;

import lombok.extern.slf4j.Slf4j;
import me.vrnsky.server.domain.User;
import me.vrnsky.server.dto.UserCreationRequest;
import me.vrnsky.server.exception.UserCreationException;
import me.vrnsky.server.exception.UserNotFoundException;
import me.vrnsky.server.repository.interfaces.UserRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Slf4j
public class UserService {

    private final UserRepo repository;

    @Autowired
    public UserService(final UserRepo repository) {
        this.repository = repository;
    }

    public void registerOrUpdate(UserCreationRequest userCreationRequest) {
        Assert.notNull(userCreationRequest, "user creation request must be not null!");
        if (StringUtils.isBlank(userCreationRequest.getEmail())) {
            log.error("Request doesn't contain required fields [{email}]");
            throw new UserCreationException("Invalid request. Missing parameter [{email}]");
        }
        if (userCreationRequest.getPassword().equals("")) {
            log.error("Request doesn't contain required fields [{password}]");
            throw new UserCreationException("Invalid request. Missing parameter [{password}]");
        }
        User user = new User(userCreationRequest.getEmail(), userCreationRequest.getPassword());
        repository.save(user);
    }

    public User getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with given id - [%s] not exists!", id)));
    }

    public User findByCredits(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }

    public void deleteUser(User user) {
        repository.delete(user);
    }
}
