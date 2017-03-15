package com.rb;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ruth on 14/03/17.
 */
public class AnimalType {

    @ApiModelProperty(
            dataType = "java.lang.String",
            example = "zebra",
            required = true)
    public String name;
}
