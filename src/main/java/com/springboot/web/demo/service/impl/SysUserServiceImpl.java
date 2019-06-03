package com.springboot.web.demo.service.impl;

import com.springboot.web.demo.entity.SysUser;
import com.springboot.web.demo.dao.SysUserDao;
import com.springboot.web.demo.service.SysUserService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (SysUser)表服务实现类
 *
 * @author makejava
 * @since 2019-06-03 14:43:05
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao,SysUser> implements SysUserService {
   
    @Autowired
    private SysUserDao sysUserDao;

   
}