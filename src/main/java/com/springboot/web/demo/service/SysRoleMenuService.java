package com.springboot.web.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.web.demo.model.entity.SysRoleMenu;


/**
 * 角色菜单表(SysRoleMenu)表服务接口
 *
 * @author penghui
 * @since 2019-06-17 10:44:49
 */
public interface SysRoleMenuService extends IService<SysRoleMenu>{

    Boolean removeRoleMenu(Integer menuId);

    Boolean updateRoleMenu(Integer roleId, String menuIds);
}