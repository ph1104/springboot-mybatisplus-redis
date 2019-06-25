package com.springboot.web.demo.controller;


import com.springboot.web.demo.model.dto.DeptTree;
import com.springboot.web.demo.model.entity.SysDept;
import com.springboot.web.demo.service.SysDeptService;
import com.springboot.web.demo.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 部门管理
 *
 * @author penghui
 * @since 2019-06-17 10:43:29
 */
@RestController
@RequestMapping("/sysDept")
@Api(tags = "部门管理")
public class SysDeptController {


    @Autowired
    private SysDeptService sysDeptService;



    /**
     * 添加部门
     *
     * @param sysDept 实体
     *
     */
    @PostMapping
    @ApiOperation(value = "添加部门信息")
    public R<Boolean> saveDept(@ApiParam(value = "部门实体") @RequestBody SysDept sysDept) {
        return new R<>(sysDeptService.saveDept(sysDept));
    }



    /**
     * 返回树形部门集合
     *
     * @return 树形部门
     */
    @GetMapping(value = "/deptTree")
    @ApiOperation(value = "查询部门列表")
    public R<List<DeptTree>> deptTree() {
        return new R<>(sysDeptService.deptTree());
    }




    /**
     * 删除部门信息
     *
     * @param id ID
     *
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除部门信息")
    public R<Boolean> removeDept(@PathVariable Integer id) {
        return new R<>(sysDeptService.removeDept(id));
    }

    /**
     * 编辑部门信息
     *
     * @param sysDept 实体
     */
    @PutMapping
    @ApiOperation(value = "编辑部门信息")
    public R<Boolean> updateDept(@ApiParam(value = "部门实体") @RequestBody SysDept sysDept) {
        return new R<>(sysDeptService.updateDept(sysDept));
    }



}