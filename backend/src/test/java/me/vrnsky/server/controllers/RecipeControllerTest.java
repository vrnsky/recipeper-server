package me.vrnsky.server.controllers;

import me.vrnsky.server.domain.Recipe;
import me.vrnsky.server.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeControllerTest {

    private RecipeService recipeService;
    private RecipeController recipeController;


    @Before
    public void setUp() {
        recipeService = mock(RecipeService.class);
        recipeController = new RecipeController(recipeService);
    }

    @Test
    public void whenTryAddRecipeShouldCheckThatServiceLayerInvokeCorrectMethod() {
        Recipe recipeMock = mock(Recipe.class);
        recipeController.addRecipe(recipeMock);
        verify(recipeService).create(recipeMock);
    }

    @Test
    public void whenTryGetListShouldCheckThatIsEmptyCollectionIfItNotFilled() {
        List<Recipe> recipes = recipeController.list();
        verify(recipeService).list();
        assertThat(recipes.size(), is(0));
    }


}