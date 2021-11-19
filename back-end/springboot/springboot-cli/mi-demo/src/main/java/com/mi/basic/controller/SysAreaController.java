package com.mi.basic.controller;


import com.mi.basic.domain.SysArea;
import com.mi.basic.dto.AreaAddParam;
import com.mi.basic.dto.AreaParam;
import com.mi.basic.service.ISysAreaService;
import com.mi.basic.vo.AreaTree;
import com.mi.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 区域管理
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/basic/area")
@Api(tags = "SysAreaController", description = "区域管理")
public class SysAreaController {

    @Autowired
    private ISysAreaService areaService;

    @ApiOperation(value = "列表树查询")
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public CommonResult tree(@RequestParam(value = "level", defaultValue = "2") Integer level){
        List<AreaTree> areaTrees = this.areaService.tree(level);
        return CommonResult.success(areaTrees);
    }

    @ApiOperation(value = "子树查询")
    @RequestMapping(value = "/tree/{parent}", method = RequestMethod.GET)
    public CommonResult treeChildren(@PathVariable Long parent){
        List<AreaTree> areaTrees = this.areaService.treeChildren(parent);
        return CommonResult.success(areaTrees);
    }

    @ApiOperation(value = "区域详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public CommonResult<SysArea> detail(@PathVariable Long id){
        SysArea area = this.areaService.getById(id);
        if (area == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(area);
    }

    @ApiOperation(value = "添加区域")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CommonResult save(@RequestBody AreaAddParam param){
        boolean success = this.areaService.save(param);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "区域更新")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody AreaParam param){
        boolean success = this.areaService.update(id, param);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "区域删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable Long id){
        boolean success = this.areaService.delete(id);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
}

