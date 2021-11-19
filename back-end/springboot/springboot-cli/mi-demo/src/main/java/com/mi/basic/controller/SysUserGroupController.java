package com.mi.basic.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mi.basic.domain.SysUserGroup;
import com.mi.basic.dto.UserGroupAddParam;
import com.mi.basic.dto.UserGroupParam;
import com.mi.basic.service.ISysUserGroupService;
import com.mi.common.api.CommonPage;
import com.mi.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 /**
 * @Description 用户组管理
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/basic/userGroup")
@Api(tags = "SysUserGroupController", description = "用户组管理")
public class SysUserGroupController {

    private ISysUserGroupService userGroupService;

    @ApiOperation(value = "用户组列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<SysUserGroup>> list(@RequestParam(value = "searchVal", required = false) String searchVal,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        Page<SysUserGroup> userGroups = this.userGroupService.list(searchVal, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(userGroups));
    }

    @ApiOperation(value = "用户组详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public CommonResult<SysUserGroup> detail(@PathVariable Long id){
        SysUserGroup userGroup = this.userGroupService.getById(id);
        if (userGroup == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(userGroup);
    }

    @ApiOperation(value = "用户组新增")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CommonResult save(@RequestBody UserGroupAddParam param){
        boolean success = this.userGroupService.save(param);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "用户组更新")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody UserGroupParam param){
        boolean success = this.userGroupService.update(id, param);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "用户组删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable Long id){
        boolean success = this.userGroupService.delete(id);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
}

