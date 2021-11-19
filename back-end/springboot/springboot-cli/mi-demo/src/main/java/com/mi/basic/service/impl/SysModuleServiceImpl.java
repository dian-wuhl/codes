package com.mi.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mi.basic.dto.ModuleAddParam;
import com.mi.basic.dto.ModuleParam;
import com.mi.basic.mapper.SysModuleMapper;
import com.mi.basic.domain.SysModule;
import com.mi.basic.service.ISysModuleService;
import com.mi.basic.vo.ModuleTree;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 分页通用对象
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
@Service
public class SysModuleServiceImpl extends ServiceImpl<SysModuleMapper, SysModule> implements ISysModuleService {

    @Override
    public List<ModuleTree> tree(Integer level) {
        List<ModuleTree> moduleTrees = null;
        //获取parent为空的数据
        QueryWrapper<SysModule> wrapper = new QueryWrapper<>();
        wrapper.lambda().isNull(SysModule::getParent);
        List<SysModule> modules = this.list(wrapper);
        if(!CollectionUtils.isEmpty(modules)){
            moduleTrees = new ArrayList<>();
            for(SysModule module : modules){
                ModuleTree moduleTree = new ModuleTree();
                BeanUtils.copyProperties(module, moduleTree);
                //遍历数组,获取子树数组
                if(level == 2) moduleTree.setChildren(this.treeChildren(module.getId()));
                moduleTrees.add(moduleTree);
            }
        }
        return moduleTrees;
    }

    @Override
    public List<ModuleTree> treeChildren(Long parent) {
        List<ModuleTree> moduleTrees = null;
        QueryWrapper<SysModule> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysModule::getParent, parent);
        List<SysModule> modules = this.list(wrapper);
        if(!CollectionUtils.isEmpty(modules)){
            moduleTrees = new ArrayList<>();
            for(SysModule module : modules){
                ModuleTree moduleTree = new ModuleTree();
                BeanUtils.copyProperties(module, moduleTree);
                moduleTrees.add(moduleTree);
            }
        }
        return moduleTrees;
    }

    @Override
    public boolean save(ModuleAddParam param) {
        SysModule module = new SysModule();
        BeanUtils.copyProperties(param, module);
        //查询是否存在相同用户名
        QueryWrapper<SysModule> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysModule::getCode, param.getCode());
        List<SysModule> modules = list(wrapper);
        if(modules.size() > 0) return false;
        return this.save(module);
    }

    @Override
    public boolean update(Long id, ModuleParam param) {
        SysModule module = new SysModule();
        BeanUtils.copyProperties(param, module);
        module.setId(id);
        return this.updateById(module);
    }

    @Override
    public boolean delete(Long id) {
        return this.removeById(id);
    }

}
