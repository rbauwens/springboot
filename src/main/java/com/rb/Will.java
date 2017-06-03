package com.rb;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ruth on 28/04/17.
 */
public class Will {

    @ApiModelProperty(
            dataType = "java.lang.Integer",
            example = "1",
            required = false)
    public Integer index;

    @ApiModelProperty(
            dataType = "java.lang.String",
            example = "John Smith",
            required = true)
    public String name;


    @ApiModelProperty(
            dataType = "java.lang.String",
            example = "1600",
            required = true)
    public int date;

    @ApiModelProperty(
            dataType = "java.lang.String",
            example = "DCN 67/11 m 1d",
            required = true)
    public String reference;

    public Will(Integer index, String name, int date, String reference) {
        this.index = index;
        this.name = name;
        this.date = date;
        this.reference = reference;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDate() { return date; }

    public void setDate(int date) { this.date = date; }

    public String getReference() { return reference; }

    public void setReference(String reference) { this.reference = reference; }
}
