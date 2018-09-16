package me.vrnsky.server.repository.interfaces;

import me.vrnsky.server.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepo extends CrudRepository<Recipe, Long> {
}
