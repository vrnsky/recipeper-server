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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest extends DatabaseTest {

    @Autowired
    private ProductService productService;

    @Test
    public void whenCreateAProductShouldCheckThatProductWasAdded() {
        Product product = new Product("potato", "from ground");
        productService.create(product);
        Product actual = productService.findById(product.getId());
        assertThat(actual, is(product));
    }

    @Test
    public void whenUpdateProductShouldCheckThatIsWasUpdated() {
        Product product = new Product("potato", "from ground");
        productService.create(product);
        product.setName("tomato");
        productService.update(product);
        Product updated = productService.findById(product.getId());
        assertThat(updated.getName(), is("tomato"));
    }

    @Test
    public void whenTryFindByDescriptionShouldCheckThatReturnedAllProducts() {
        Product potato = new Product("potato", "from ground");
        Product tomato = new Product("tomato", "from ground");
        productService.create(potato);
        productService.create(tomato);
        List<Product> actual = productService.findByDescription("from ground");
        assertThat(actual.size(), is(2));
    }


    @Test(expected = ProductNotFoundException.class)
    public void whenRemoveProductFromDatabaseShouldCheckThatProductDeleted() {
        Product product = new Product("potato", "from ground");
        productService.create(product);
        productService.delete(product);
        Product deleted = productService.findById(product.getId());
    }
}