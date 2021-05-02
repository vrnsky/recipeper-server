package me.vrnsky.server.service;

import lombok.RequiredArgsConstructor;
import me.vrnsky.server.domain.Product;
import me.vrnsky.server.exception.ProductNotFoundException;
import me.vrnsky.server.repository.interfaces.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public void create(Product product) {
        productRepo.save(product);
    }

    public void update(Product product) {
        productRepo.save(product);
    }

    public Product findById(Long id) {
        return productRepo.findById(id).
                orElseThrow(() -> new ProductNotFoundException(String.format("Product with [%s] not exist!", id)));
    }

    public List<Product> findByDescription(String desc) {
        return productRepo.findByDescription(desc).
                orElseThrow(() -> new ProductNotFoundException(String.format("Product with given desc [%s] not exist!", desc)));
    }

    public void delete(Product product) {
        productRepo.delete(product);
    }
}
