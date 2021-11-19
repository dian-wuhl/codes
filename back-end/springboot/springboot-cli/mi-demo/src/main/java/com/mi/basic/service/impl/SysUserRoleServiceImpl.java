package com.mi.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mi.basic.domain.SysUserRole;
import com.mi.basic.dto.UserRoleAddParam;
import com.mi.basic.dto.UserRoleParam;
import com.mi.basic.mapper.SysUserRoleMapper;
import com.mi.basic.service.ISysUserRoleService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 角色管理
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @Override
    public Page<SysUserRole> list(String searchVal, Integer pageSize, Integer pageNum) {
        Page<SysUserRole> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SysUserRole> wrapperLambda = wrapper.lambda();
        if(StringUtils.isNotBlank(searchVal)){
            wrapperLambda.eq(SysUserRole::getCode, searchVal);
            wrapperLambda.or().eq(SysUserRole::getName, searchVal);
        }
        return page(page, wrapper);
    }

    @Override
    public boolean save(UserRoleAddParam param) {
        SysUserRole userRole = new SysUserRole();
        BeanUtils.copyProperties(param, userRole);
        //查询是否存在相同用户名
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUserRole::getCode, param.getCode());
        List<SysUserRole> userRoles = list(wrapper);
        if(userRoles.size() > 0) return false;
        return this.save(userRole);
    }

    @Override
    public boolean update(Long id, UserRoleParam param) {
        SysUserRole userRole = new SysUserRole();
        BeanUtils.copyProperties(param, userRole);
        userRole.setId(id);
        return this.updateById(userRole);
    }

    @Override
    public boolean delete(Long id) {
        return this.removeById(id);
    }
}
