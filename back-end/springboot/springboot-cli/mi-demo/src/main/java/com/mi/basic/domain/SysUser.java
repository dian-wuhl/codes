package com.mi.basic.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 /**
 * @Description 分页通用对象
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
@ApiModel(value="SysUser对象", description="")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "created_by", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人")
    private Long createdBy;

    @TableField(value = "created_on", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createdOn;

    @TableField(value = "updated_by", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新人")
    private Long updatedBy;

    @TableField(value = "updated_on", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updatedOn;

    @ApiModelProperty(value = "是否可用（1-可用；0-不可用）")
    private Integer enabled;

    @Version
    @ApiModelProperty(value = "更新次数，线程安全使用")
    private Integer updateCount;

    @ApiModelProperty(value = "登录名")
    private String userCode;

    @ApiModelProperty(value = "密码")
    private String userPwd;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "性别（1-男；0-女；-1-保密）")
    private Integer gender;

    @ApiModelProperty(value = "手机号码")
    private Long phoneNumber;

    @ApiModelProperty(value = "固定电话")
    private String fixedTelephone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "区域")
    private Long area;

    @ApiModelProperty(value = "所属公司")
    private Long company;

    @ApiModelProperty(value = "所属部门")
    private Long department;

    @ApiModelProperty(value = "出生日期")
    private Date birthday;

    @ApiModelProperty(value = "用户头像")
    private String userAvatar;


}
