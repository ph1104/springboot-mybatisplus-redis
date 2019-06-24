package com.springboot.web.demo.springsecurity.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.web.demo.dao.SysUserDao;
import com.springboot.web.demo.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 自定义用户登录逻辑
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserDao.selectOne(new QueryWrapper<SysUser>().eq("name",username));
        if(sysUser != null){
            String password = passwordEncoder.encode(sysUser.getPassword());
            return new User(username,password,AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
        }else{
            throw new UsernameNotFoundException("用户名或手机号不存在");
        }

    }
}
