package me.vrnsky.server.controllers.dto.recipe;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import me.vrnsky.server.controllers.dto.BaseResponse;
import me.vrnsky.server.domain.Recipe;

@Data
public class CreateResponse extends BaseResponse {

    @ApiModelProperty(value = "Созданный рецепт")
    private Recipe recipe;
}
