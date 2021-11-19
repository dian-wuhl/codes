// Copyright 2016-2101 mi.
package com.mi.basic.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @Description 用户登录此参数
 * @Author Dian
 * @Date 2021/9/6 10:38
 * @ModifyDate 2021/9/6 10:38
 * @Version 1.0
 */
@Getter
@Setter
public class UserParam {

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "性别（1-男；0-女；-1-保密）")
    private Integer gender;

    @ApiModelProperty(value = "手机号码")
    private Long phoneNumber;

    @ApiModelProperty(value = "固定电话")
    private String fixedTelephone;

    @Email
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "城市")
    private Long area;

    @ApiModelProperty(value = "所属部门")
    private Long department;

    @ApiModelProperty(value = "出生日期")
    private Date birthday;
}
