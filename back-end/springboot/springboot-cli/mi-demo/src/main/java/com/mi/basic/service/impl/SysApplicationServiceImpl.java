package com.mi.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mi.basic.domain.SysApplication;
import com.mi.basic.dto.ApplicationAddParam;
import com.mi.basic.dto.ApplicationParam;
import com.mi.basic.mapper.SysApplicationMapper;
import com.mi.basic.service.ISysApplicationService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 应用管理
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
@Service
public class SysApplicationServiceImpl extends ServiceImpl<SysApplicationMapper, SysApplication> implements ISysApplicationService {

    @Override
    public Page<SysApplication> list(String searchVal, Integer pageSize, Integer pageNum) {
        Page<SysApplication> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SysApplication> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SysApplication> wrapperLambda = wrapper.lambda();
        if(StringUtils.isNotBlank(searchVal)){
            wrapperLambda.eq(SysApplication::getCode, searchVal);
            wrapperLambda.or().eq(SysApplication::getName, searchVal);
        }
        return page(page, wrapper);
    }

    @Override
    public boolean save(ApplicationAddParam param) {
        SysApplication application = new SysApplication();
        BeanUtils.copyProperties(param, application);
        //查询是否存在相同用户名
        QueryWrapper<SysApplication> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysApplication::getCode, param.getCode());
        List<SysApplication> applications = list(wrapper);
        if(applications.size() > 0) return false;
        return this.save(application);
    }

    @Override
    public boolean update(Long id, ApplicationParam param) {
        SysApplication application = new SysApplication();
        BeanUtils.copyProperties(param, application);
        application.setId(id);
        return this.updateById(application);
    }

    @Override
    public boolean delete(Long id) {
        return this.removeById(id);
    }
}
