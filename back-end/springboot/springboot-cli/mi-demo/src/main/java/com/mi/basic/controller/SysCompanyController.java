package com.mi.basic.controller;


import com.mi.basic.domain.SysCompany;
import com.mi.basic.domain.SysUser;
import com.mi.basic.dto.CompanyAddParam;
import com.mi.basic.dto.CompanyParam;
import com.mi.basic.service.ISysCompanyService;
import com.mi.basic.vo.CompanyTree;
import com.mi.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 公司管理
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/basic/company")
@Api(tags = "SysCompanyController", description = "公司管理")
public class SysCompanyController {

    @Autowired
    private ISysCompanyService companyService;

    @ApiOperation(value = "列表树查询")
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public CommonResult tree(@RequestParam(value = "level", defaultValue = "2") Integer level){
        List<CompanyTree> companyTrees = this.companyService.tree(level);
        return CommonResult.success(companyTrees);
    }

    @ApiOperation(value = "子树查询")
    @RequestMapping(value = "/tree/{parent}", method = RequestMethod.GET)
    public CommonResult treeChildren(@PathVariable Long parent){
        List<CompanyTree> companyTrees = this.companyService.treeChildren(parent);
        return CommonResult.success(companyTrees);
    }

    @ApiOperation(value = "公司详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public CommonResult<SysCompany> detail(@PathVariable Long id){
        SysCompany company = this.companyService.getById(id);
        if (company == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(company);
    }

    @ApiOperation(value = "添加公司")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CommonResult save(@RequestBody CompanyAddParam param){
        boolean success = this.companyService.save(param);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "公司更新")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult<SysUser> update(@PathVariable Long id, @RequestBody CompanyParam param){
        boolean success = this.companyService.update(id, param);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "公司删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable Long id){
        boolean success = this.companyService.delete(id);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
}

