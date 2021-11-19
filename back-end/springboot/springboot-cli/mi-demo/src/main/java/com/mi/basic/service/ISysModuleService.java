package com.mi.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mi.basic.domain.SysModule;
import com.mi.basic.dto.ModuleAddParam;
import com.mi.basic.dto.ModuleParam;
import com.mi.basic.vo.ModuleTree;

import java.util.List;

/**
 * @Description 模块管理
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
public interface ISysModuleService extends IService<SysModule> {

    /**
     * 模块列表树查询
     * @param level
     * @return
     */
    List<ModuleTree> tree(Integer level);

    /**
     * 模块子树查询
     * @param parent
     * @return
     */
    List<ModuleTree> treeChildren(Long parent);

    /**
     * 新增模块
     * @param param
     * @return
     */
    boolean save(ModuleAddParam param);

    /**
     * 更新模块
     * @param id
     * @param param
     * @return
     */
    boolean update(Long id, ModuleParam param);

    /**
     * 删除模块
     * @param id
     * @return
     */
    boolean delete(Long id);

}
