// Copyright 2016-2101 mi.
package com.mi.basic.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description 公司列表树
 * @Author Dian
 * @Date 2021/9/7 15:01
 * @ModifyDate 2021/9/7 15:01
 * @Version 1.0
 */
@Getter
@Setter
public class CompanyTree {

    private Long id;
    private Long parent;
    private String name;
    private String code;
    private List<CompanyTree> children;
}
