package me.vrnsky.server.service;

import me.vrnsky.server.domain.Recipe;
import me.vrnsky.server.repository.interfaces.RecipeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class RecipeService {

    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

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

    public List<Recipe> list() {
        for (Recipe recipe : recipeRepo.findAll()) {
            logger.debug(recipe.toString());
        }
        return recipeRepo.findAll();
    }
}
