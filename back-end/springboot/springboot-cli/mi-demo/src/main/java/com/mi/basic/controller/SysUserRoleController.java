package com.mi.basic.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mi.basic.domain.SysUserRole;
import com.mi.basic.dto.UserRoleAddParam;
import com.mi.basic.dto.UserRoleParam;
import com.mi.basic.service.ISysUserRoleService;
import com.mi.common.api.CommonPage;
import com.mi.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 角色管理
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/basic/userRole")
@Api(tags = "SysUserRoleController", description = "角色管理")
public class SysUserRoleController {

    @Autowired
    private ISysUserRoleService userRoleService;

    @ApiOperation(value = "角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<SysUserRole>> list(@RequestParam(value = "searchVal", required = false) String searchVal,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        Page<SysUserRole> userRoles = this.userRoleService.list(searchVal, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(userRoles));
    }

    @ApiOperation(value = "角色详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public CommonResult<SysUserRole> detail(@PathVariable Long id){
        SysUserRole userRole = this.userRoleService.getById(id);
        if (userRole == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(userRole);
    }

    @ApiOperation(value = "角色新增")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CommonResult save(@RequestBody UserRoleAddParam param){
        boolean success = this.userRoleService.save(param);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "角色更新")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody UserRoleParam param){
        boolean success = this.userRoleService.update(id, param);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "角色删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable Long id){
        boolean success = this.userRoleService.delete(id);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

}

