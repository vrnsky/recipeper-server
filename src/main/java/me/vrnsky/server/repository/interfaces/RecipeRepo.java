package me.vrnsky.server.repository.interfaces;

import me.vrnsky.server.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepo extends CrudRepository<Recipe, Long> {

    @Override
    List<Recipe> findAll();
}
