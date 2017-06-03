package com.rb;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ruth on 06/05/17.
 */
public class WillResponse extends Response{

    @ApiModelProperty(
            dataType = "java.lang.String",
            required = false)
    public Will will;

}