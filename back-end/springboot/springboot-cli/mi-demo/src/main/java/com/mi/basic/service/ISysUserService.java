package com.mi.basic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mi.basic.domain.SysUser;
import com.mi.basic.dto.UserLoginParam;
import com.mi.basic.dto.UserParam;
import com.mi.basic.dto.UserPwdUpdateParam;
import com.mi.basic.dto.UserRegisterParam;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Description 分页通用对象
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 用户列表查询
     * @param searchVal
     * @param pageSize
     * @param pageNum
     * @return
     */
    Page<SysUser> list(String searchVal, Integer pageSize, Integer pageNum);

    /**
     * 用户注册
     * @param param
     * @return
     */
    SysUser register(UserRegisterParam param);

    /**
     * 用户更新
     * @param id
     * @param param
     * @return
     */
    boolean update(Long id, UserParam param);

    /**
     * 用户密码修改
     * @param param
     * @return
     */
    int updatePassword(UserPwdUpdateParam param);

    /**
     * 用户删除
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 根据登录名获取用户信息
     * @param userCode
     * @return
     */
    SysUser getByUserCode(String userCode);

    /**
     * 用户登录
     * @param param
     * @return
     */
    SysUser login(UserLoginParam param);
}
