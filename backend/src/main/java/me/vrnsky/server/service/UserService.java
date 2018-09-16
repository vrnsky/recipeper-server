package me.vrnsky.server.service;

import me.vrnsky.server.domain.User;
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

    public void registerOrUpdate(User user) {
        repository.save(user);
    }

    public User getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("User with given id not exists!"));
    }

    public User findByCredits(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }

    public void deleteUser(User user) {
        repository.delete(user);
    }
}
