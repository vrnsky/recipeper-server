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

    public void addUser(User user) {
        this.repository.save(user);
    }

    public void editUser(User user) {
        //TODO implement
    }

    public User getById(int id) {
        return this.repository.findById(id);
    }

    public void deleteUser(User user) {
        this.repository.delete(user);
    }
}
