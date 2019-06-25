package com.springboot.web.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.web.demo.model.entity.SysUser;
import com.springboot.web.demo.model.vo.UserVO;


/**
 * 用户表(SysUser)表服务接口
 *
 * @author penghui
 * @since 2019-06-17 10:46:27
 */
public interface SysUserService extends IService<SysUser>{

    UserVO getUserInfoById(Integer id);
}