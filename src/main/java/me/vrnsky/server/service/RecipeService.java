package me.vrnsky.server.service;

import lombok.RequiredArgsConstructor;
import me.vrnsky.server.domain.Recipe;
import me.vrnsky.server.exception.RecipeNotFoundException;
import me.vrnsky.server.repository.interfaces.RecipeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepo recipeRepo;

    public void create(Recipe recipe) {
        recipeRepo.save(recipe);
    }

    public Recipe read(Long id) {
        return recipeRepo.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe with given id not exists!"));
    }

    public void update(Recipe recipe) {
        recipeRepo.save(recipe);
    }

    public void delete(Recipe recipe) {
        recipeRepo.delete(recipe);
    }

    public List<Recipe> list() {
        return recipeRepo.findAll();
    }
}
