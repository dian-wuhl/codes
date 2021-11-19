// Copyright 2016-2101 mi.
package com.mi.basic.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @Description 应用对象
 * @Author Dian
 * @Date 2021/9/6 10:38
 * @ModifyDate 2021/9/6 10:38
 * @Version 1.0
 */
@Getter
@Setter
public class ApplicationParam {

    @NotEmpty
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}
