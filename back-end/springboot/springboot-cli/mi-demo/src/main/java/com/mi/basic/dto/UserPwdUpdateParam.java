// Copyright 2016-2101 mi.
package com.mi.basic.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @Description 用户密码修改
 * @Author Dian
 * @Date 2021/9/6 15:14
 * @ModifyDate 2021/9/6 15:14
 * @Version 1.0
 */
@Getter
@Setter
public class UserPwdUpdateParam {

    @NotEmpty
    @ApiModelProperty(value = "用户名", required = true)
    private String userCode;

    @NotEmpty
    @ApiModelProperty(value = "旧密码", required = true)
    private String oldPwd;

    @NotEmpty
    @ApiModelProperty(value = "新密码", required = true)
    private String newPwd;

}
