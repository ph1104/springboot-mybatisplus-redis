package com.springboot.web.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.web.demo.model.entity.SysRole;


/**
 * 系统角色表(SysRole)表服务接口
 *
 * @author penghui
 * @since 2019-06-17 10:44:36
 */
public interface SysRoleService extends IService<SysRole>{

    Boolean removeMenu(Integer roleId);


}