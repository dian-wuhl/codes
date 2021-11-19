package com.mi.basic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mi.basic.domain.SysUserRole;
import com.mi.basic.dto.UserRoleAddParam;
import com.mi.basic.dto.UserRoleParam;

/**
 * @Description 用户角色管理
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    /**
     * 分页查询
     * @param searchVal
     * @param pageSize
     * @param pageNum
     * @return
     */
    Page<SysUserRole> list(String searchVal, Integer pageSize, Integer pageNum);

    /**
     * 角色新增
     * @param param
     * @return
     */
    boolean save(UserRoleAddParam param);

    /**
     * 角色更新
     * @param id
     * @param param
     * @return
     */
    boolean update(Long id, UserRoleParam param);

    /**
     * 角色删除
     * @param id
     * @return
     */
    boolean delete(Long id);

}
