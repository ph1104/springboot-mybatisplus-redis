package com.springboot.web.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.web.demo.model.entity.SysUser;
import com.springboot.web.demo.model.vo.UserVO;


/**
 * 用户表(SysUser)表数据库访问层
 *
 * @author penghui
 * @since 2019-06-17 10:46:27
 */
public interface SysUserDao extends BaseMapper<SysUser>{

    UserVO getUserInfoById(Integer id);
}