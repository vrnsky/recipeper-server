package me.vrnsky.server.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.vrnsky.server.controllers.dto.recipe.CreateResponse;
import me.vrnsky.server.controllers.dto.recipe.GetRecipeResponse;
import me.vrnsky.server.controllers.dto.recipe.ListResponse;
import me.vrnsky.server.controllers.dto.recipe.UpdateResponse;
import me.vrnsky.server.domain.Recipe;
import me.vrnsky.server.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping(value = "/create")
    @ApiOperation(value = "Добавление нового рецепта")
    public CreateResponse addRecipe(@RequestBody @Valid Recipe recipe) {
        return recipeService.create(recipe);
    }

    @PutMapping(value = "/update")
    @ApiOperation(value = "Обновление существующего рецепта")
    public UpdateResponse updateRecipe(@RequestBody @Valid Recipe recipe) {
        return recipeService.update(recipe);
    }

    @GetMapping("/list")
    @ApiOperation(value = "Получение всех рецептов")
    public ListResponse list() {
        return recipeService.list();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Получение рецепта по ID")
    public GetRecipeResponse getById(@PathVariable Long id) {
        return recipeService.read(id);
    }
}
