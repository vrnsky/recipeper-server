package me.vrnsky.server.repository.interfaces;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findById(int id);

    User findByEmailAndPassword(String email, String password);
}
