package me.vrnsky.server.repository.interfaces;

import me.vrnsky.server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
    User findById(int id);
}
