package com.springboot.web.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.web.demo.dao.SysUserRoleDao;
import com.springboot.web.demo.model.entity.SysUserRole;
import com.springboot.web.demo.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户角色表(SysUserRole)表服务实现类
 *
 * @author penghui
 * @since 2019-06-17 10:44:58
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {
   
    @Autowired
    private SysUserRoleDao sysUserRoleDao;

   
}