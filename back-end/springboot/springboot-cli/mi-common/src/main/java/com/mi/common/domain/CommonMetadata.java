// Copyright 2016-2101 mi.
package com.mi.common.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 数据对象基类
 * @Author Dian
 * @Date 2021/8/30 15:07
 * @ModifyDate 2021/8/30 15:07
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonMetadata implements Serializable  {

    @TableId(value = "id")
    @ApiModelProperty(value = "主键")
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
    @ApiModelProperty(value = "是否可用")
    private Integer active;
    @Version
    @ApiModelProperty(value = "线程安全标识")
    private Integer version;

}
