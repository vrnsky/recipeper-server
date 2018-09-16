package me.vrnsky.server.service;

import me.vrnsky.server.domain.Product;
import me.vrnsky.server.repository.interfaces.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo repo) {
        this.productRepo = repo;
    }

    public void create(Product product) {
        productRepo.save(product);
    }

    public void update(Product product) {
        productRepo.save(product);
    }

    public Product findById(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Product with given id not exist!"));
    }

    public List<Product> findByDescription(String desc) {
        return productRepo.findByDescription(desc).orElseThrow(() -> new IllegalArgumentException("Product with given desc not exist!"));
    }

    public void delete(Product product) {
        productRepo.delete(product);
    }
}
