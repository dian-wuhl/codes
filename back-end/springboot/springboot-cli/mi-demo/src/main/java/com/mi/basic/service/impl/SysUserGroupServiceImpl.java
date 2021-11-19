package com.mi.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mi.basic.domain.SysUserGroup;
import com.mi.basic.dto.UserGroupAddParam;
import com.mi.basic.dto.UserGroupParam;
import com.mi.basic.mapper.SysUserGroupMapper;
import com.mi.basic.service.ISysUserGroupService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 用户组管理
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
@Service
public class SysUserGroupServiceImpl extends ServiceImpl<SysUserGroupMapper, SysUserGroup> implements ISysUserGroupService {

    @Override
    public Page<SysUserGroup> list(String searchVal, Integer pageSize, Integer pageNum) {
        Page<SysUserGroup> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SysUserGroup> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SysUserGroup> wrapperLambda = wrapper.lambda();
        if(StringUtils.isNotBlank(searchVal)){
            wrapperLambda.eq(SysUserGroup::getCode, searchVal);
            wrapperLambda.or().eq(SysUserGroup::getName, searchVal);
        }
        return page(page, wrapper);
    }

    @Override
    public boolean save(UserGroupAddParam param) {
        SysUserGroup userGroup = new SysUserGroup();
        BeanUtils.copyProperties(param, userGroup);
        //查询是否存在相同用户名
        QueryWrapper<SysUserGroup> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUserGroup::getCode, param.getCode());
        List<SysUserGroup> userGroups = list(wrapper);
        if(userGroups.size() > 0) return false;
        return this.save(userGroup);
    }

    @Override
    public boolean update(Long id, UserGroupParam param) {
        SysUserGroup userGroup = new SysUserGroup();
        BeanUtils.copyProperties(param, userGroup);
        userGroup.setId(id);
        return this.updateById(userGroup);
    }

    @Override
    public boolean delete(Long id) {
        return this.removeById(id);
    }
}
