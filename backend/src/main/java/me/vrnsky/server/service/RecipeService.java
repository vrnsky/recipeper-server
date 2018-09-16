package me.vrnsky.server.service;

import me.vrnsky.server.domain.Recipe;
import me.vrnsky.server.repository.interfaces.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private RecipeRepo recipeRepo;

    @Autowired
    public RecipeService(RecipeRepo repo) {
        this.recipeRepo = repo;
    }

    public void create(Recipe recipe) {
        recipeRepo.save(recipe);
    }

    public Recipe read(Long id) {
        return recipeRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Recipe with given id not exists!"));
    }

    public void update(Recipe recipe) {
        recipeRepo.save(recipe);
    }

    public void delete(Recipe recipe) {
        recipeRepo.delete(recipe);
    }
}
