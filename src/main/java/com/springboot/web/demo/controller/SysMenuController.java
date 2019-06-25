package com.springboot.web.demo.controller;


import com.springboot.web.demo.model.dto.MenuTree;
import com.springboot.web.demo.model.entity.SysMenu;
import com.springboot.web.demo.service.SysMenuService;
import com.springboot.web.demo.util.R;
import com.springboot.web.demo.util.TreeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 菜单权限表(SysMenu)表控制层
 *
 * @author penghui
 * @since 2019-06-17 10:44:30
 */
@RestController
@RequestMapping("/sysMenu")
@Api(tags = "菜单管理")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;


    /**
     * 添加菜单
     *
     * @param sysMenu 实体
     *
     */
    @PostMapping
    @ApiOperation(value = "添加菜单信息")
    public R<Boolean> saveMenu(@ApiParam(value = "菜单实体") @RequestBody SysMenu sysMenu) {
        return new R<>(sysMenuService.save(sysMenu));
    }



    /**
     * 返回树形菜单集合
     *
     * @return 树形菜单
     */
    @GetMapping(value = "/deptTree")
    @ApiOperation(value = "查询菜单列表")
    public R<List<MenuTree>> menuTree() {
        return new R<>(TreeUtil.buildTree(sysMenuService.list(),-1));
    }




    /**
     * 删除菜单信息
     *
     * @param id
     *
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除菜单信息")
    public R<Boolean> removeMenu(@PathVariable Integer id) {
        return new R<>(sysMenuService.removeMenu(id));
    }


    /**
     * 编辑菜单信息
     *
     * @param sysMenu 实体
     */
    @PutMapping
    @ApiOperation(value = "编辑菜单信息")
    public R<Boolean> updateMenu(@ApiParam(value = "菜单实体") @RequestBody SysMenu sysMenu) {
        return new R<>(sysMenuService.updateById(sysMenu));
    }



}
