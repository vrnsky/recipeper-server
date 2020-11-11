package me.vrnsky.server.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.vrnsky.server.domain.Recipe;
import me.vrnsky.server.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addRecipe(@ModelAttribute Recipe recipe) {
        recipeService.create(recipe);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
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
    @ResponseStatus(HttpStatus.OK)
    public Recipe updateRecipe(@ModelAttribute Recipe recipe) {
        recipeService.update(recipe);
        return recipeService.read(recipe.getId());
    }


}
