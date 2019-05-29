package com.springboot.web.demo.service.impl;

import com.springboot.web.demo.entity.User;
import com.springboot.web.demo.dao.UserDao;
import com.springboot.web.demo.service.UserService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2019-05-28 11:16:36
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService {
   
    @Autowired
    private UserDao userDao;

   
}