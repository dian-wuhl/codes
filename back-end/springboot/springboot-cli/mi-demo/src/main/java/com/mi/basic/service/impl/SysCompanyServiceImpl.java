package com.mi.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mi.basic.domain.SysCompany;
import com.mi.basic.dto.CompanyAddParam;
import com.mi.basic.dto.CompanyParam;
import com.mi.basic.mapper.SysCompanyMapper;
import com.mi.basic.service.ISysCompanyService;
import com.mi.basic.vo.CompanyTree;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 公司管理
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
@Service
public class SysCompanyServiceImpl extends ServiceImpl<SysCompanyMapper, SysCompany> implements ISysCompanyService {

    @Override
    public List<CompanyTree> tree(Integer level) {
        List<CompanyTree> companyTrees = null;
        //获取parent为空的数据
        QueryWrapper<SysCompany> wrapper = new QueryWrapper<>();
        wrapper.lambda().isNull(SysCompany::getParent);
        List<SysCompany> companies = this.list(wrapper);
        if(!CollectionUtils.isEmpty(companies)){
            companyTrees = new ArrayList<>();
            for(SysCompany company : companies){
                CompanyTree companyTree = new CompanyTree();
                BeanUtils.copyProperties(company, companyTree);
                //遍历数组,获取子树数组
                if(level == 2) companyTree.setChildren(this.treeChildren(company.getId()));
                companyTrees.add(companyTree);
            }
        }
        return companyTrees;
    }

    @Override
    public List<CompanyTree> treeChildren(Long parent) {
        List<CompanyTree> companyTrees = null;
        QueryWrapper<SysCompany> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysCompany::getParent, parent);
        List<SysCompany> companies = this.list(wrapper);
        if(!CollectionUtils.isEmpty(companies)){
            companyTrees = new ArrayList<>();
            for(SysCompany company : companies){
                CompanyTree companyTree = new CompanyTree();
                BeanUtils.copyProperties(company, companyTree);
                companyTrees.add(companyTree);
            }
        }
        return companyTrees;
    }

    @Override
    public Page<SysCompany> list(String searchVal, Integer pageSize, Integer pageNum) {
        Page<SysCompany> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SysCompany> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SysCompany> wrapperLambda = wrapper.lambda();
        if(StringUtils.isNotBlank(searchVal)){
            wrapperLambda.eq(SysCompany::getCode, searchVal);
            wrapperLambda.or().eq(SysCompany::getName, searchVal);
        }
        return page(page, wrapper);
    }

    @Override
    public boolean save(CompanyAddParam param) {
        SysCompany company = new SysCompany();
        BeanUtils.copyProperties(param, company);
        //查询是否存在相同标识
        QueryWrapper<SysCompany> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysCompany::getCode, param.getCode());
        List<SysCompany> companies = list(wrapper);
        if(companies.size() > 0) return false;
        return this.save(company);
    }

    @Override
    public boolean update(Long id, CompanyParam param) {
        SysCompany company = new SysCompany();
        BeanUtils.copyProperties(param, company);
        company.setId(id);
        return this.updateById(company);
    }

    @Override
    public boolean delete(Long id) {
        return this.removeById(id);
    }
}
