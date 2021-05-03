package me.vrnsky.server.controllers.dto.recipe;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.vrnsky.server.controllers.dto.BaseResponse;
import me.vrnsky.server.domain.Recipe;

import java.util.List;

@Data
public class ListResponse extends BaseResponse {

    @ApiModelProperty(value = "Список всех рецептов")
    private List<Recipe> recipes;
}
