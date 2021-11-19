// Copyright 2016-2101 mi.
package com.mi.basic.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @Description 用户注册
 * @Author Dian
 * @Date 2021/9/6 10:38
 * @ModifyDate 2021/9/6 10:38
 * @Version 1.0
 */
@Getter
@Setter
public class UserRegisterParam {

    @NotEmpty
    @ApiModelProperty(value = "登录名", required = true)
    private String userCode;

    @NotEmpty
    @ApiModelProperty(value = "密码", required = true)
    private String userPwd;

    @Email
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号码")
    private Long phoneNumber;
}
