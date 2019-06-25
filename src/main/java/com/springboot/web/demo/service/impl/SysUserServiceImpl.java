package com.springboot.web.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.web.demo.dao.SysUserDao;
import com.springboot.web.demo.model.entity.SysUser;
import com.springboot.web.demo.model.vo.UserVO;
import com.springboot.web.demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户表(SysUser)表服务实现类
 *
 * @author penghui
 * @since 2019-06-17 10:46:27
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {
   
    @Autowired
    private SysUserDao sysUserDao;


    /**
     * 通过ID查询用户信息
     * @param id
     * @return
     */
    @Override
    public UserVO getUserInfoById(Integer id) {
        return sysUserDao.getUserInfoById(id);
    }
}