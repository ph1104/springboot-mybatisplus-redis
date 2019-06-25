package com.springboot.web.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.web.demo.dao.SysRoleMenuDao;
import com.springboot.web.demo.model.entity.SysRoleMenu;
import com.springboot.web.demo.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色菜单表(SysRoleMenu)表服务实现类
 *
 * @author penghui
 * @since 2019-06-17 10:44:49
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenu> implements SysRoleMenuService {
   
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;


    /**
     * 删除角色权限关联信息
     * @param menuId
     * @return
     */
    @Override
    public Boolean removeRoleMenu(Integer menuId) {
        return sysRoleMenuDao.removeRoleMenu(menuId);
    }


    /**
     * 更新角色菜单(全删全插)
     * @param roleId
     * @param menuIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateRoleMenu(Integer roleId, String menuIds) {
       sysRoleMenuDao.delete(new QueryWrapper<SysRoleMenu>()
               .lambda()
               .eq(SysRoleMenu::getRoleId,roleId));
        List<SysRoleMenu> roleMenuList = Arrays
                .stream(menuIds.split(","))
                .map(menuId -> {
                    SysRoleMenu roleMenu = new SysRoleMenu();
                    roleMenu.setRoleId(roleId);
                    roleMenu.setMenuId(Integer.valueOf(menuId));
                    return roleMenu;
                }).collect(Collectors.toList());
        this.saveBatch(roleMenuList);
        return true;
    }
}