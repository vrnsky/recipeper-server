package me.vrnsky.server.controllers.dto.recipe;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.vrnsky.server.controllers.dto.BaseResponse;
import me.vrnsky.server.domain.Recipe;

@Data
public class UpdateResponse extends BaseResponse {

    @ApiModelProperty(value = "Обновленный рецепт")
    private Recipe recipe;
}
