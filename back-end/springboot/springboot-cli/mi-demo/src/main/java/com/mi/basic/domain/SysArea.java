package com.mi.basic.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 区域对象
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_area")
@ApiModel(value="SysArea", description="")
public class SysArea implements Serializable {

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

    @ApiModelProperty(value = "归属")
    private Long parent;

    @ApiModelProperty(value = "区域类型（0-国家；1-省级；2-市级；3-县级）")
    private Integer type;
}
