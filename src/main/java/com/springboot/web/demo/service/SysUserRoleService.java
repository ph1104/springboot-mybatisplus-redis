package com.springboot.web.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.web.demo.model.entity.SysUserRole;
import com.springboot.web.demo.model.vo.UserVO;


/**
 * 用户角色表(SysUserRole)表服务接口
 *
 * @author penghui
 * @since 2019-06-17 10:44:58
 */
public interface SysUserRoleService extends IService<SysUserRole>{

   Boolean saveUserRole(UserVO userVO);
}