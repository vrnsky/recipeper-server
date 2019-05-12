package me.vrnsky.server.repository.interfaces;

import me.vrnsky.server.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findById(int id);

    User findByUsername(String username);
}
