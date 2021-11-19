package com.mi.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mi.basic.domain.SysArea;
import com.mi.basic.dto.AreaAddParam;
import com.mi.basic.dto.AreaParam;
import com.mi.basic.vo.AreaTree;

import java.util.List;

/**
 * @Description 区域管理
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
public interface ISysAreaService extends IService<SysArea> {

    /**
     * 区域列表树查询
     * @param level
     * @return
     */
    List<AreaTree> tree(Integer level);

    /**
     * 区域子树查询
     * @param parent
     * @return
     */
    List<AreaTree> treeChildren(Long parent);

    /**
     * 新增区域
     * @param param
     * @return
     */
    boolean save(AreaAddParam param);

    /**
     * 更新区域
     * @param id
     * @param param
     * @return
     */
    boolean update(Long id, AreaParam param);

    /**
     * 删除区域
     * @param id
     * @return
     */
    boolean delete(Long id);
}
