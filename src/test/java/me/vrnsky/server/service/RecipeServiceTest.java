package me.vrnsky.server.service;

import me.vrnsky.server.DatabaseTest;
import me.vrnsky.server.controllers.dto.recipe.GetRecipeResponse;
import me.vrnsky.server.domain.Recipe;
import me.vrnsky.server.exception.RecipeNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceTest extends DatabaseTest {

    @Autowired
    private RecipeService recipeService;

    @Test
    public void whenCreateARecipeShouldCheckThatWasAdded() {
        Recipe recipe = new Recipe("Вкусная картошка", "Это очень вкусная картошка");
        recipeService.create(recipe);
        GetRecipeResponse actual = recipeService.read(recipe.getId());
        assertEquals("Вкусная картошка",actual.getRecipe().getTitle());
    }

    @Test
    public void whenUpdateRecipeShouldCheckThatIsWasUpdated() {
        Recipe recipe = new Recipe("1", "2");
        recipeService.create(recipe);
        recipe.setDescription("3");
        recipeService.update(recipe);
        GetRecipeResponse actual = recipeService.read(recipe.getId());
        assertEquals("3", actual.getRecipe().getDescription());
    }

    @Test(expected = RecipeNotFoundException.class)
    public void whenRemoveRecipeShouldCheckThatIsRemoved() {
        Recipe recipe = new Recipe("1", "2");
        recipeService.create(recipe);
        recipeService.delete(recipe);
        recipeService.read(recipe.getId());
    }
}