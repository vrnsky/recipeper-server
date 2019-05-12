package me.vrnsky.server.service;

import me.vrnsky.server.domain.User;
import me.vrnsky.server.exception.UserNotFoundException;
import me.vrnsky.server.repository.interfaces.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    private UserRepo repository;
    private PasswordEncoder passwordEncoder;
    private EmailService emailService;

    @Autowired
    public UserService(UserRepo repository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        emailService.send(user.getEmail(), "Welcome", "To the Recipeper");
    }

    public User getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with given id - [%s] not exists!", id)));
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public void deleteUser(User user) {
        repository.delete(user);
    }
}
