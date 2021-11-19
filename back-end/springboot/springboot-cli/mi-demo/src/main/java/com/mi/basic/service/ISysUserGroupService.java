package com.mi.basic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mi.basic.domain.SysUserGroup;
import com.mi.basic.dto.UserGroupAddParam;
import com.mi.basic.dto.UserGroupParam;

/**
 * @Description 用户组管理
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
public interface ISysUserGroupService extends IService<SysUserGroup> {

    /**
     * 分页查询
     * @param searchVal
     * @param pageSize
     * @param pageNum
     * @return
     */
    Page<SysUserGroup> list(String searchVal, Integer pageSize, Integer pageNum);

    /**
     * 用户组新增
     * @param param
     * @return
     */
    boolean save(UserGroupAddParam param);

    /**
     * 用户组更新
     * @param id
     * @param param
     * @return
     */
    boolean update(Long id, UserGroupParam param);

    /**
     * 用户组删除
     * @param id
     * @return
     */
    boolean delete(Long id);
}
