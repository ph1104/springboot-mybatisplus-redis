package com.springboot.web.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.web.demo.model.entity.SysRole;
import com.springboot.web.demo.service.SysRoleMenuService;
import com.springboot.web.demo.service.SysRoleService;
import com.springboot.web.demo.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 系统角色表(SysRole)表控制层
 *
 * @author penghui
 * @since 2019-06-17 10:44:36
 */
@RestController
@RequestMapping("/sysRole")
@Api(tags = "角色管理")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;


    /**
     * 添加菜单
     *
     * @param sysRole 实体
     *
     */
    @PostMapping
    @ApiOperation(value = "添加角色信息")
    public R<Boolean> saveRole(@ApiParam(value = "角色实体") @RequestBody SysRole sysRole) {
        return new R<>(sysRoleService.save(sysRole));
    }



    /**
     * 查询角色列表
     *
     * @return
     */
    @GetMapping(value = "/deptTree")
    @ApiOperation(value = "查询角色列表")
    public R<IPage<SysRole>> roleList(@ApiParam(value="page",required = false)@RequestParam(required = false,defaultValue = "0") Integer  page,
                                      @ApiParam(value="limit",required = false)@RequestParam(required = false,defaultValue = "20") Integer  limit
    ){
        return new R<>(sysRoleService.page(new Page<>(page,limit)));
    }




    /**
     * 删除角色信息
     *
     * @param id
     *
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除角色信息")
    public R<Boolean> removeMenu(@PathVariable Integer id) {
        return new R<>(sysRoleService.removeMenu(id));
    }


    /**
     * 编辑角色信息
     *
     * @param sysRole 实体
     */
    @PutMapping
    @ApiOperation(value = "编辑角色信息")
    public R<Boolean> updateMenu(@ApiParam(value = "角色实体") @RequestBody SysRole sysRole) {
        return new R<>(sysRoleService.updateById(sysRole));
    }


    /**
     * 更新角色权限
     *
     */
    @PutMapping("/updateRoleMenu")
    @ApiOperation(value = "更新角色权限")
    public R<Boolean> updateRoleMenu(@ApiParam(value = "角色ID") @RequestParam(value = "roleId") Integer roleId,
                                     @ApiParam(value = "菜单ID拼成的字符串，每个id之间根据逗号分隔") @RequestParam(value = "menuIds",required = false) String menuIds
    ){
        return new R<>(sysRoleMenuService.updateRoleMenu(roleId,menuIds));
    }


}