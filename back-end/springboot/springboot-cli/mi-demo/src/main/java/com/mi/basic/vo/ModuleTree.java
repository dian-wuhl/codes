// Copyright 2016-2101 mi.
package com.mi.basic.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description 模块列表树
 * @Author Dian
 * @Date 2021/9/7 15:01
 * @ModifyDate 2021/9/7 15:01
 * @Version 1.0
 */
@Getter
@Setter
public class ModuleTree {

    private Long id;
    private Long application;
    private Long parent;
    private String name;
    private String code;
    private String path;
    private String icon;
    private List<ModuleTree> children;
}
