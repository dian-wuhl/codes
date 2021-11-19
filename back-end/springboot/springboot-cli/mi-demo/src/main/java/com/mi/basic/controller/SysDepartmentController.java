package com.mi.basic.controller;


import com.mi.basic.domain.SysDepartment;
import com.mi.basic.dto.DepartmentAddParam;
import com.mi.basic.dto.DepartmentParam;
import com.mi.basic.service.ISysDepartmentService;
import com.mi.basic.vo.DepartmentTree;
import com.mi.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 部门管理
 * @Author Dian
 * @Date 2021/9/2 10:00
 * @ModifyDate 2021/9/2 10:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/basic/department")
@Api(tags = "SysDepartmentController", description = "部门管理")
public class SysDepartmentController {

    @Autowired
    private ISysDepartmentService departmentService;

    @ApiOperation(value = "列表树查询")
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public CommonResult tree(@RequestParam(value = "level", defaultValue = "2") Integer level){
        List<DepartmentTree> departmentTrees = this.departmentService.tree(level);
        return CommonResult.success(departmentTrees);
    }

    @ApiOperation(value = "子树查询")
    @RequestMapping(value = "/tree/{parent}", method = RequestMethod.GET)
    public CommonResult treeChildren(@PathVariable Long parent){
        List<DepartmentTree> departmentTrees = this.departmentService.treeChildren(parent);
        return CommonResult.success(departmentTrees);
    }

    @ApiOperation(value = "部门详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public CommonResult<SysDepartment> detail(@PathVariable Long id){
        SysDepartment department = this.departmentService.getById(id);
        if (department == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(department);
    }

    @ApiOperation(value = "添加部门")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CommonResult save(@RequestBody DepartmentAddParam param){
        boolean success = this.departmentService.save(param);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "部门更新")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody DepartmentParam param){
        boolean success = this.departmentService.update(id, param);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "部门删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable Long id){
        boolean success = this.departmentService.delete(id);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }


}

