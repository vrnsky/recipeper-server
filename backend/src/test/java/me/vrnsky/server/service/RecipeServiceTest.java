package me.vrnsky.server.service;

import me.vrnsky.server.domain.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceTest {

    @Autowired
    private RecipeService recipeService;

    @Test
    public void whenCreateARecipeShouldCheckThatWasAdded() {
        Recipe recipe = new Recipe("potato with tomato", "step 1. clear");
        recipeService.create(recipe);
        Recipe actual = recipeService.read(recipe.getId());
        assertThat(actual.getTitle(), is("potato with tomato"));
    }

    @Test
    public void whenUpdateRecipeShouldCheckThatIsWasUpdated() {
        Recipe recipe = new Recipe("1", "2");
        recipeService.create(recipe);
        recipe.setDescription("3");
        recipeService.update(recipe);
        Recipe actual = recipeService.read(recipe.getId());
        assertThat(actual.getDescription(), is("3"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenRemoveRecipeShouldCheckThatIsRemoved() {
        Recipe recipe = new Recipe("1", "2");
        recipeService.create(recipe);
        recipeService.delete(recipe);
        assertThat(recipeService.read(recipe.getId()), is(nullValue()));
    }
}