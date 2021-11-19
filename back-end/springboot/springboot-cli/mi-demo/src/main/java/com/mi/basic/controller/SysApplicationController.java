package com.mi.basic.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mi.basic.domain.SysApplication;
import com.mi.basic.domain.SysUser;
import com.mi.basic.dto.ApplicationAddParam;
import com.mi.basic.dto.ApplicationParam;
import com.mi.basic.service.ISysApplicationService;
import com.mi.common.api.CommonPage;
import com.mi.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 /**
 * @Description 应用管理
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/basic/application")
@Api(tags = "SysApplicationController", description = "应用管理")
public class SysApplicationController {

    @Autowired
    private ISysApplicationService applicationService;

    @ApiOperation(value = "应用列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<SysApplication>> list(@RequestParam(value = "searchVal", required = false) String searchVal,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        Page<SysApplication> applications = this.applicationService.list(searchVal, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(applications));
    }

    @ApiOperation(value = "应用详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public CommonResult<SysApplication> detail(@PathVariable Long id){
        SysApplication application = this.applicationService.getById(id);
        if (application == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(application);
    }

    @ApiOperation(value = "应用新增")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CommonResult save(@RequestBody ApplicationAddParam param){
        boolean success = this.applicationService.save(param);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "应用更新")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult<SysUser> update(@PathVariable Long id, @RequestBody ApplicationParam param){
        boolean success = this.applicationService.update(id, param);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "应用删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable Long id){
        boolean success = this.applicationService.delete(id);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
}

