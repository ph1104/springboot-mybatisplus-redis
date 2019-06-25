package com.springboot.web.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.web.demo.model.entity.SysMenu;


/**
 * 菜单权限表(SysMenu)表服务接口
 *
 * @author penghui
 * @since 2019-06-17 10:44:30
 */
public interface SysMenuService extends IService<SysMenu>{
    Boolean removeMenu(Integer menuId);
   
}