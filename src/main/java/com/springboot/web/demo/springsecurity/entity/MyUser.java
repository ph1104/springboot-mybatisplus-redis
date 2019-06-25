package com.springboot.web.demo.springsecurity.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 拓展spring security的User信息
 */
public class MyUser extends User {
    /**
     * 用户ID
     */
    @Getter
    private Integer id;
    /**
     * 部门ID
     */
    @Getter
    private Integer deptId;


    public MyUser(Integer id, Integer deptId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.deptId = deptId;
    }


    public MyUser(Integer id, Integer deptId, String username, String password,Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.deptId = deptId;
    }
}