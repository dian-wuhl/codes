// Copyright 2016-2101 mi.
package com.mi.basic.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @Description 区域对象
 * @Author Dian
 * @Date 2021/9/6 10:38
 * @ModifyDate 2021/9/6 10:38
 * @Version 1.0
 */
@Getter
@Setter
public class AreaParam {

    @NotEmpty
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "所属区域")
    private Long parent;

    @ApiModelProperty(value = "区域类型（0-国家；1-省级；2-市级；3-县级）")
    private Integer type;
}
