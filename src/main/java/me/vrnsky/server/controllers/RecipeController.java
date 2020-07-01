package me.vrnsky.server.controllers;

import me.vrnsky.server.domain.Recipe;
import me.vrnsky.server.dto.GetRecipeRequest;
import me.vrnsky.server.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(final RecipeService service) {
        this.recipeService = service;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addRecipe(@RequestBody Recipe recipe) {
        recipeService.create(recipe);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public List<Recipe> list() {
        return recipeService.list();
    }

    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Recipe getById(@RequestBody GetRecipeRequest recipeRequest) {
        return recipeService.read(recipeRequest.getId());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Recipe updateRecipe(@RequestBody Recipe recipe) {
        recipeService.update(recipe);
        return recipeService.read(recipe.getId());
    }


}
