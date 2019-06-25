package com.springboot.web.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.web.demo.dao.SysRoleDao;
import com.springboot.web.demo.model.entity.SysRole;
import com.springboot.web.demo.model.entity.SysRoleMenu;
import com.springboot.web.demo.service.SysRoleMenuService;
import com.springboot.web.demo.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统角色表(SysRole)表服务实现类
 *
 * @author penghui
 * @since 2019-06-17 10:44:36
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {
   
    @Autowired
    private SysRoleDao sysRoleDao;


    @Autowired
    private SysRoleMenuService sysRoleMenuService;



    /**
     * 删除角色
     * @param roleId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeMenu(Integer roleId) {
        //删除角色
        this.removeById(roleId);
        //删除角色权限关联表
        sysRoleMenuService.remove(new QueryWrapper<SysRoleMenu>()
                .lambda()
                .eq(SysRoleMenu::getRoleId,roleId));
        return true;
    }


}