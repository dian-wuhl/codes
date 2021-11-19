package com.mi.basic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mi.basic.domain.SysCompany;
import com.mi.basic.dto.CompanyAddParam;
import com.mi.basic.dto.CompanyParam;
import com.mi.basic.vo.CompanyTree;

import java.util.List;

/**
 * @Description 公司管理
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
public interface ISysCompanyService extends IService<SysCompany> {

    /**
     * 区域列表树查询
     * @param level
     * @return
     */
    List<CompanyTree> tree(Integer level);

    /**
     * 区域子树查询
     * @param parent
     * @return
     */
    List<CompanyTree> treeChildren(Long parent);

    /**
     * 分页查询
     * @param searchVal
     * @param pageSize
     * @param pageNum
     * @return
     */
    Page<SysCompany> list(String searchVal, Integer pageSize, Integer pageNum);

    /**
     * 公司新增
     * @param param
     * @return
     */
    boolean save(CompanyAddParam param);

    /**
     * 公司更新
     * @param id
     * @param param
     * @return
     */
    boolean update(Long id, CompanyParam param);

    /**
     * 公司删除
     * @param id
     * @return
     */
    boolean delete(Long id);
}
