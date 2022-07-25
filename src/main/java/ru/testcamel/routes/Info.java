package ru.testcamel.routes;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Info {

    @ApiModelProperty(value = "Service version", example = "1.0.0")
    private String version;
}