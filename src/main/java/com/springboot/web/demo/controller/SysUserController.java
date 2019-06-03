package com.springboot.web.demo.controller;

import com.springboot.web.demo.entity.SysUser;
import com.springboot.web.demo.service.SysUserService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (SysUser)表控制层
 *
 * @author makejava
 * @since 2019-06-03 14:43:05
 */
@RestController
@RequestMapping("sysUser")
@Api(tags = "SysUserController  controller")
public class SysUserController {
    /**
     * 服务对象
     */
    @Autowired
    private SysUserService sysUserService;

}