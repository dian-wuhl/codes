// Copyright 2016-2101 mi.
package com.mi.basic.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @Description 公司新增
 * @Author Dian
 * @Date 2021/9/6 10:38
 * @ModifyDate 2021/9/6 10:38
 * @Version 1.0
 */
@Getter
@Setter
public class CompanyAddParam {

    @NotEmpty
    @ApiModelProperty(value = "名称")
    private String name;

    @NotEmpty
    @ApiModelProperty(value = "标识")
    private String code;

    @ApiModelProperty(value = "所属公司")
    private Long parent;
}
