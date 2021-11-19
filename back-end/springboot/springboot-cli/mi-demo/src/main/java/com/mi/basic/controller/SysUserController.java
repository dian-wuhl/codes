package com.mi.basic.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mi.basic.domain.SysUser;
import com.mi.basic.dto.UserParam;
import com.mi.basic.dto.UserPwdUpdateParam;
import com.mi.basic.dto.UserRegisterParam;
import com.mi.basic.service.ISysUserService;
import com.mi.common.api.CommonPage;
import com.mi.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 分页通用对象
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/basic/user")
@Api(tags = "SysUserController", description = "用户管理")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @ApiOperation(value = "用户信息获取")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public CommonResult userInfo(){
        Map<String, Object> result = new HashMap<>();
        JSONObject principal = (JSONObject) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userCode = principal.getString("username");
        SysUser user = this.sysUserService.getByUserCode(userCode);
        if(user != null) {
            result.put("name", user.getName());
            result.put("userAvatar", user.getUserAvatar());
        }
        return CommonResult.success(result);
    }

    @ApiOperation(value = "退出")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public CommonResult logout(){
        Map<String, Object> result = new HashMap<>();
        JSONObject principal = (JSONObject) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userCode = principal.getString("username");
        SysUser user = this.sysUserService.getByUserCode(userCode);
        if(user != null) {
            result.put("name", user.getName());
            result.put("userAvatar", user.getUserAvatar());
        }
        return CommonResult.success(result);
    }

    @ApiOperation(value = "用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<SysUser>> list(@RequestParam(value = "searchVal", required = false) String searchVal,
                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        Page<SysUser> users = this.sysUserService.list(searchVal, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(users));
    }

    @ApiOperation(value = "用户详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public CommonResult<SysUser> detail(@PathVariable Long id){
        SysUser user = this.sysUserService.getById(id);
        if (user == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(user);
    }

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<SysUser> register(@RequestBody UserRegisterParam param){
        SysUser user = this.sysUserService.register(param);
        if (user == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(user);
    }

    @ApiOperation(value = "用户更新")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult<SysUser> update(@PathVariable Long id, @RequestBody UserParam param){
        boolean success = this.sysUserService.update(id, param);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "用户密码修改")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public CommonResult updatePassword(@RequestBody UserPwdUpdateParam param){
        int status = this.sysUserService.updatePassword(param);
        if (status > 0) {
            return CommonResult.success(status);
        } else if (status == -1) {
            return CommonResult.failed("提交参数不合法");
        } else if (status == -2) {
            return CommonResult.failed("找不到该用户");
        } else if (status == -3) {
            return CommonResult.failed("旧密码错误");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "用户删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable Long id){
        boolean success = this.sysUserService.delete(id);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }


    @ApiOperation(value = "测试")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public CommonResult<SysUser> test(){
        return CommonResult.success(new SysUser());
    }
}

