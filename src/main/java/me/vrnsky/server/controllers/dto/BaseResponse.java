package me.vrnsky.server.controllers.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseResponse {

    @ApiModelProperty(value = "Описание ошибки")
    private String errorMessage;

    @ApiModelProperty(value = "Код ошибки")
    private int errorCode;
}
