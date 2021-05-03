package me.vrnsky.server.service;

import me.vrnsky.server.DatabaseTest;
import me.vrnsky.server.domain.Product;
import me.vrnsky.server.exception.ProductNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest extends DatabaseTest {

    @Autowired
    private ProductService productService;

    @Test
    public void whenCreateAProductShouldCheckThatProductWasAdded() {
        Product product = new Product("potato", "from ground");
        productService.create(product);
        Product actualProduct = productService.findById(product.getId());
        assertEquals("Product was not saved properly", product, actualProduct);
    }

    @Test
    public void whenUpdateProductShouldCheckThatIsWasUpdated() {
        Product product = new Product("potato", "from ground");
        productService.create(product);
        product.setName("tomato");
        productService.update(product);
        Product updated = productService.findById(product.getId());
        assertEquals("Product was not updated properly", "tomato", updated.getName());
    }

    @Test
    public void whenTryFindByDescriptionShouldCheckThatReturnedAllProducts() {
        Product potato = new Product("potato", "from ground");
        Product tomato = new Product("tomato", "from ground");
        productService.create(potato);
        productService.create(tomato);
        List<Product> actual = productService.findByDescription("from ground");
        assertEquals("Should be 2 products", 2, actual.size());
    }


    @Test(expected = ProductNotFoundException.class)
    public void whenRemoveProductFromDatabaseShouldCheckThatProductDeleted() {
        Product product = new Product("potato", "from ground");
        productService.create(product);
        productService.delete(product);
        productService.findById(product.getId());
    }
}