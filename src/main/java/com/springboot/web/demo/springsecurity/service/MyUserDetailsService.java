package com.springboot.web.demo.springsecurity.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.web.demo.dao.SysUserDao;
import com.springboot.web.demo.model.entity.SysUser;
import com.springboot.web.demo.springsecurity.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {



    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 自定义用户登录（用户名密码登录）逻辑
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserDao.selectOne(new QueryWrapper<SysUser>().eq("username",username));
        if(user != null){
            //String password = passwordEncoder.encode(sysUser.getPassword());
            return new MyUser(user.getUserId(),user.getDeptId(),user.getUsername(),user.getPassword(),AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
        }else{
            throw new UsernameNotFoundException("用户名或手机号不存在");
        }

    }
}
