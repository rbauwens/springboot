package com.rb;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ruth on 14/03/17.
 */
public class Animal {

    @ApiModelProperty(
            dataType = "java.lang.Integer",
            example = "1",
            required = false)
    public Integer index;

    @ApiModelProperty(
            dataType = "java.lang.String",
            example = "zebra",
            required = true)
    public String name;
}
