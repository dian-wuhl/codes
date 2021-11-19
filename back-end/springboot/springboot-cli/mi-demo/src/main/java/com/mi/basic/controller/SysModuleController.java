package com.mi.basic.controller;


import com.mi.basic.domain.SysModule;
import com.mi.basic.dto.ModuleAddParam;
import com.mi.basic.dto.ModuleParam;
import com.mi.basic.service.ISysModuleService;
import com.mi.basic.vo.ModuleTree;
import com.mi.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 模块管理
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/basic/module")
@Api(tags = "SysModuleController", description = "模块管理")
public class SysModuleController {

    private ISysModuleService moduleService;

    @ApiOperation(value = "列表树查询")
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public CommonResult tree(@RequestParam(value = "level", defaultValue = "2") Integer level){
        List<ModuleTree> moduleTrees = this.moduleService.tree(level);
        return CommonResult.success(moduleTrees);
    }

    @ApiOperation(value = "子树查询")
    @RequestMapping(value = "/tree/{parent}", method = RequestMethod.GET)
    public CommonResult treeChildren(@PathVariable Long parent){
        List<ModuleTree> moduleTrees = this.moduleService.treeChildren(parent);
        return CommonResult.success(moduleTrees);
    }

    @ApiOperation(value = "模块详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public CommonResult<SysModule> detail(@PathVariable Long id){
        SysModule module = this.moduleService.getById(id);
        if (module == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(module);
    }

    @ApiOperation(value = "添加模块")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CommonResult save(@RequestBody ModuleAddParam param){
        boolean success = this.moduleService.save(param);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "模块更新")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody ModuleParam param){
        boolean success = this.moduleService.update(id, param);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "模块删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable Long id){
        boolean success = this.moduleService.delete(id);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }


}

