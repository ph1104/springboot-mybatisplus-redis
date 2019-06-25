package com.springboot.web.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.web.demo.model.entity.SysRoleMenu;


/**
 * 角色菜单表(SysRoleMenu)表数据库访问层
 *
 * @author penghui
 * @since 2019-06-17 10:44:49
 */
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenu>{
    Boolean removeRoleMenu(Integer menuId);

}