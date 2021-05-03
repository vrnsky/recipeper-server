package me.vrnsky.server.service;

import lombok.RequiredArgsConstructor;
import me.vrnsky.server.controllers.dto.recipe.CreateResponse;
import me.vrnsky.server.controllers.dto.recipe.GetRecipeResponse;
import me.vrnsky.server.controllers.dto.recipe.ListResponse;
import me.vrnsky.server.controllers.dto.recipe.UpdateResponse;
import me.vrnsky.server.domain.Recipe;
import me.vrnsky.server.exception.RecipeNotFoundException;
import me.vrnsky.server.repository.interfaces.RecipeRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepo recipeRepo;

    public CreateResponse create(Recipe recipe) {
        Recipe savedRecipe = recipeRepo.save(recipe);
        CreateResponse createResponse = new CreateResponse();
        createResponse.setRecipe(savedRecipe);
        createResponse.setErrorCode(0);
        createResponse.setErrorMessage("Успешно");
        return createResponse;
    }

    public GetRecipeResponse read(Long id) {
         Recipe  recipe = recipeRepo.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe with given id not exists!"));
         GetRecipeResponse getRecipeResponse = new GetRecipeResponse();
         getRecipeResponse.setRecipe(recipe);
         getRecipeResponse.setErrorMessage("Успешно");
         getRecipeResponse.setErrorCode(0);
         return getRecipeResponse;
    }

    public UpdateResponse update(Recipe recipe) {
        Recipe updateRecipe = recipeRepo.save(recipe);
        UpdateResponse updateResponse = new UpdateResponse();
        updateResponse.setRecipe(updateRecipe);
        updateResponse.setErrorCode(0);
        updateResponse.setErrorMessage("Успешно");
        return updateResponse;
    }

    public void delete(Recipe recipe) {
        recipeRepo.delete(recipe);
    }

    public ListResponse list() {
        ListResponse listResponse = new ListResponse();
        listResponse.setRecipes(recipeRepo.findAll());
        listResponse.setErrorCode(0);
        listResponse.setErrorMessage("Успешно");
        return listResponse;
    }
}
