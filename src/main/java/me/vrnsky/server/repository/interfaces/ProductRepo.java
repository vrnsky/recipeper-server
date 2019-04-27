package me.vrnsky.server.repository.interfaces;

import me.vrnsky.server.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends CrudRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long aLong);

    Optional<List<Product>> findByDescription(String description);

}
