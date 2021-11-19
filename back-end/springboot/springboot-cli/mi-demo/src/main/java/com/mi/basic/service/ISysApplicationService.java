package com.mi.basic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mi.basic.domain.SysApplication;
import com.mi.basic.dto.ApplicationAddParam;
import com.mi.basic.dto.ApplicationParam;

/**
 * @Description 应用管理
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
public interface ISysApplicationService extends IService<SysApplication> {

    /**
     * 分页查询
     * @param searchVal
     * @param pageSize
     * @param pageNum
     * @return
     */
    Page<SysApplication> list(String searchVal, Integer pageSize, Integer pageNum);

    /**
     * 应用新增
     * @param param
     * @return
     */
    boolean save(ApplicationAddParam param);

    /**
     * 应用更新
     * @param id
     * @param param
     * @return
     */
    boolean update(Long id, ApplicationParam param);

    /**
     * 应用删除
     * @param id
     * @return
     */
    boolean delete(Long id);
}
