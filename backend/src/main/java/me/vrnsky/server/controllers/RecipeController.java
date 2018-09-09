package me.vrnsky.server.controllers;

import me.vrnsky.server.domain.Recipe;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Recipe addRecipe() {
        return new Recipe();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Recipe> list() {
        return new ArrayList<>();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Recipe getById(@PathVariable("id") int id) {
        return new Recipe();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Recipe updateRecipe(@PathVariable("id") int id) {
        return new Recipe();
    }


}
