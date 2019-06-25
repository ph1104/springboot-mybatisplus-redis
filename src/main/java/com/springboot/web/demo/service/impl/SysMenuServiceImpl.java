package com.springboot.web.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.web.demo.dao.SysMenuDao;
import com.springboot.web.demo.model.entity.SysMenu;
import com.springboot.web.demo.service.SysMenuService;
import com.springboot.web.demo.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(SysMenu)表服务实现类
 *
 * @author penghui
 * @since 2019-06-17 10:44:30
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysMenuDao sysMenuDao;


    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeMenu(Integer menuId) {
        //查询当前菜单节点是哪些节点的父节点
        List<Integer> menuIds = sysMenuDao.selectList(new QueryWrapper<SysMenu>()
                .lambda()
                .eq(SysMenu::getParentId,menuId)
                .or()
                .eq(SysMenu::getMenuId,menuId))
                .stream()
                .map(SysMenu::getMenuId)
                .collect(Collectors.toList());
        //级联删除(修改状态)
        this.removeByIds(menuIds);
        //删除角色菜单关联表
        sysRoleMenuService.removeRoleMenu(menuId);
        return true;
    }
}