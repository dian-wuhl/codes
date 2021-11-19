package com.mi.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mi.basic.domain.SysArea;
import com.mi.basic.dto.AreaAddParam;
import com.mi.basic.dto.AreaParam;
import com.mi.basic.mapper.SysAreaMapper;
import com.mi.basic.service.ISysAreaService;
import com.mi.basic.vo.AreaTree;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 区域管理
 * @Author Dian
 * @Date 2021/9/2 11:38
 * @ModifyDate 2021/9/2 11:38
 * @Version 1.0
 */
@Service
public class SysAreaServiceImpl extends ServiceImpl<SysAreaMapper, SysArea> implements ISysAreaService {

    @Override
    public List<AreaTree> tree(Integer level) {
        List<AreaTree> areaTrees = null;
        //获取parent为空的数据
        QueryWrapper<SysArea> wrapper = new QueryWrapper<>();
        wrapper.lambda().isNull(SysArea::getParent);
        List<SysArea> sysCities = this.list(wrapper);
        if(!CollectionUtils.isEmpty(sysCities)){
            areaTrees = new ArrayList<>();
            for(SysArea area : sysCities){
                AreaTree areaTree = new AreaTree();
                BeanUtils.copyProperties(area, areaTree);
                //遍历数组,获取子树数组
                if(level == 2) areaTree.setChildren(this.treeChildren(area.getId()));
                areaTrees.add(areaTree);
            }
        }
        return areaTrees;
    }

    @Override
    public List<AreaTree> treeChildren(Long parent) {
        List<AreaTree> childAreaTrees = null;
        QueryWrapper<SysArea> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysArea::getParent, parent);
        List<SysArea> sysCities = this.list(wrapper);
        if(!CollectionUtils.isEmpty(sysCities)){
            childAreaTrees = new ArrayList<>();
            for(SysArea sysArea : sysCities){
                AreaTree areaTree = new AreaTree();
                BeanUtils.copyProperties(sysArea, areaTree);
                childAreaTrees.add(areaTree);
            }
        }
        return childAreaTrees;
    }

    @Override
    public boolean save(AreaAddParam param) {
        SysArea area = new SysArea();
        BeanUtils.copyProperties(param, area);
        //查询是否存在相同用户名
        QueryWrapper<SysArea> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysArea::getCode, param.getCode());
        List<SysArea> cities = list(wrapper);
        if(cities.size() > 0) return false;
        return this.save(area);
    }

    @Override
    public boolean update(Long id, AreaParam param) {
        SysArea area = new SysArea();
        BeanUtils.copyProperties(param, area);
        area.setId(id);
        return this.updateById(area);
    }

    @Override
    public boolean delete(Long id) {
        return this.removeById(id);
    }
}
