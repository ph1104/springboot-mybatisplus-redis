package com.springboot.web.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.web.demo.model.entity.SysMenu;

import java.util.List;

/**
 * 菜单权限表(SysMenu)表数据库访问层
 *
 * @author penghui
 * @since 2019-06-17 10:44:30
 */
public interface SysMenuDao extends BaseMapper<SysMenu>{

    List<SysMenu> listPermissionsByRoleIds(String roleIds);
}