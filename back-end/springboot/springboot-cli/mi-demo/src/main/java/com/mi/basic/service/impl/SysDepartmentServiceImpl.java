package com.mi.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mi.basic.domain.SysDepartment;
import com.mi.basic.dto.DepartmentAddParam;
import com.mi.basic.dto.DepartmentParam;
import com.mi.basic.mapper.SysDepartmentMapper;
import com.mi.basic.service.ISysDepartmentService;
import com.mi.basic.vo.DepartmentTree;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 部门管理
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
@Service
public class SysDepartmentServiceImpl extends ServiceImpl<SysDepartmentMapper, SysDepartment> implements ISysDepartmentService {

    @Override
    public List<DepartmentTree> tree(Integer level) {
        List<DepartmentTree> departmentTrees = null;
        QueryWrapper<SysDepartment> wrapper = new QueryWrapper<>();
        wrapper.lambda().isNull(SysDepartment::getParent);
        List<SysDepartment> departments = this.list(wrapper);
        if(!CollectionUtils.isEmpty(departments)){
            departmentTrees = new ArrayList<>();
            for(SysDepartment department : departments){
                DepartmentTree departmentTree = new DepartmentTree();
                BeanUtils.copyProperties(department, departmentTree);
                if(level == 2) departmentTree.setChildren(this.treeChildren(department.getId()));
                departmentTrees.add(departmentTree);
            }
        }
        return departmentTrees;
    }

    @Override
    public List<DepartmentTree> treeChildren(Long parent) {
        List<DepartmentTree> departmentTrees = null;
        QueryWrapper<SysDepartment> wrapper = new QueryWrapper();
        wrapper.lambda().eq(SysDepartment::getParent, parent);
        List<SysDepartment> departments = this.list(wrapper);
        if(!CollectionUtils.isEmpty(departments)){
            departmentTrees = new ArrayList<>();
            for(SysDepartment department : departments){
                DepartmentTree departmentTree = new DepartmentTree();
                BeanUtils.copyProperties(department, departmentTree);
                departmentTrees.add(departmentTree);
            }
        }
        return departmentTrees;
    }

    @Override
    public boolean save(DepartmentAddParam param) {
        SysDepartment department = new SysDepartment();
        BeanUtils.copyProperties(param, department);
        //查询是否存在相同用户名
        QueryWrapper<SysDepartment> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysDepartment::getCode, param.getCode());
        List<SysDepartment> departments = list(wrapper);
        if(departments.size() > 0) return false;
        return this.save(department);
    }

    @Override
    public boolean update(Long id, DepartmentParam param) {
        SysDepartment department = new SysDepartment();
        BeanUtils.copyProperties(param, department);
        department.setId(id);
        return this.updateById(department);
    }

    @Override
    public boolean delete(Long id) {
        return this.removeById(id);
    }
}
