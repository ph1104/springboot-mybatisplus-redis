package com.springboot.web.demo.config.springsecurity.sms;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 存放请求信息，以及用户登录的相关信息
 *
 * @author penghui
 * @date 2019/6/4 0004   10:08
 *
 */
public class SmsAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 510L;

    //未登录的时候存放手机号信息  登录成功后存放用户信息
    private final Object principal;


    //未登录的时候调用此构造函数 ,将手机号传给principal， Authenticated的值设置为false即未认证状态
    public SmsAuthenticationToken(String mobile) {
        super((Collection)null);
        this.principal = mobile;
        this.setAuthenticated(false);
    }

    //登录成功后调用此构造函数，principal的值是用户信息， Authenticated的值是true即已认证成功
    public SmsAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    public Object getCredentials() {
        return null;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
