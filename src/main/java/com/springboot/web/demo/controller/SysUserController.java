package com.springboot.web.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.web.demo.model.entity.SysRole;
import com.springboot.web.demo.model.entity.SysUser;
import com.springboot.web.demo.model.vo.UserVO;
import com.springboot.web.demo.service.SysUserService;
import com.springboot.web.demo.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * (SysUser)表控制层
 *
 * @author penghui
 * @since 2019-06-14 09:02:39
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    @GetMapping("/getUserByName")
    @ApiOperation(value = "根据用户名获取用户信息")
    public SysUser getUserByName(@RequestParam String username){
        SysUser sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().eq("username",username));
        return sysUser;
    }


    /**
     * 通过ID查询用户信息
     *
     * @param id ID
     * @return 用户信息
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "通过ID查询用户信息")
    public R<UserVO> user(@PathVariable Integer id) {
        return new R<>(sysUserService.getUserInfoById(id));
    }


    /**
     * 添加用户
     *
     * @param userVO 实体
     *
     */
    @PostMapping
    @ApiOperation(value = "添加用户信息")
    public R<Boolean> saveDept(@ApiParam(value = "用户实体") @RequestBody UserVO userVO) {
        return new R<>(sysUserService.saveUser(userVO));
    }


    /**
     * 删除用户信息
     *
     * @param id
     *
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户信息")
    public R<Boolean> removeUser(@PathVariable Integer id) {
        return new R<>(sysUserService.removeUser(id));
    }


    /**
     * 编辑用户信息
     *
     * @param userVO 实体
     */
    @PutMapping
    @ApiOperation(value = "编辑用户信息")
    public R<Boolean> updateUser(@ApiParam(value = "用户实体") @RequestBody UserVO userVO) {
        return new R<>(sysUserService.updateUser(userVO));
    }




}