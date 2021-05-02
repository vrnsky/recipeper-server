package me.vrnsky.server.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.vrnsky.server.domain.Recipe;
import me.vrnsky.server.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping(value = "/add")
    @ApiOperation(value = "Добавление нового рецепта")
    public void addRecipe(@RequestBody Recipe recipe) {
        recipeService.create(recipe);
    }

    @PutMapping(value = "/update")
    @ApiOperation(value = "Обновление существующего рецепта")
    public Recipe updateRecipe(@RequestBody Recipe recipe) {
        recipeService.update(recipe);
        return recipeService.read(recipe.getId());
    }

    @GetMapping("/list")
    @ApiOperation(value = "Получение всех рецептов")
    public List<Recipe> list() {
        return recipeService.list();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Получение рецепта по ID")
    public Recipe getById(@PathVariable Long id) {
        return recipeService.read(id);
    }
}
