package me.vrnsky.server.controllers;

import me.vrnsky.server.domain.Recipe;
import me.vrnsky.server.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService service) {
        this.recipeService = service;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addRecipe(@ModelAttribute Recipe recipe) {
        recipeService.create(recipe);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Recipe> list() {
        return recipeService.list();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Recipe getById(@PathVariable Long id) {
        return recipeService.read(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Recipe updateRecipe(@ModelAttribute Recipe recipe) {
        recipeService.update(recipe);
        return recipeService.read(recipe.getId());
    }


}
