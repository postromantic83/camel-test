package ru.testcamel.routes;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Info implements Serializable {

    @ApiModelProperty(value = "Service version", example = "1.0.0")
    private String version;
}