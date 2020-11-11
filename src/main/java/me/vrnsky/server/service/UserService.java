package me.vrnsky.server.service;

import me.vrnsky.server.domain.User;
import me.vrnsky.server.exception.UserNotFoundException;
import me.vrnsky.server.repository.interfaces.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepo repository;

    @Autowired
    public UserService(UserRepo repository) {
        this.repository = repository;
    }

    public void register(User user) {
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
