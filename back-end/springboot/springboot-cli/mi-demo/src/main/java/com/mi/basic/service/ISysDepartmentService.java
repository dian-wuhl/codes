package com.mi.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mi.basic.domain.SysDepartment;
import com.mi.basic.dto.DepartmentAddParam;
import com.mi.basic.dto.DepartmentParam;
import com.mi.basic.vo.DepartmentTree;

import java.util.List;

/**
 * @Description 部门管理
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
public interface ISysDepartmentService extends IService<SysDepartment> {

    /**
     * 部门列表树查询
     * @param level
     * @return
     */
    List<DepartmentTree> tree(Integer level);

    /**
     * 部门子树查询
     * @param parent
     * @return
     */
    List<DepartmentTree> treeChildren(Long parent);

    /**
     * 新增部门
     * @param param
     * @return
     */
    boolean save(DepartmentAddParam param);

    /**
     * 更新部门
     * @param id
     * @param param
     * @return
     */
    boolean update(Long id, DepartmentParam param);

    /**
     * 删除部门
     * @param id
     * @return
     */
    boolean delete(Long id);

}
