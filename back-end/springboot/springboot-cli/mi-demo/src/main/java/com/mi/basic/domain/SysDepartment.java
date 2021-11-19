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
 * @Description 分页通用对象
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_department")
@ApiModel(value="SysDepartment对象", description="")
public class SysDepartment implements Serializable {

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

    @Version
    @ApiModelProperty(value = "更新次数，线程安全使用")
    private Integer updateCount;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "标识")
    private String code;

    @ApiModelProperty(value = "所属公司")
    private Long company;

    @ApiModelProperty(value = "所属部门")
    private Long parent;

    @ApiModelProperty(value = "负责人")
    private Long leader;


}
