package com.rb;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ruth on 28/03/17.
 */
public class AnimalResponse extends Response {

    @ApiModelProperty(
            dataType = "java.lang.String",
            required = false)
    public Animal animal;

}
