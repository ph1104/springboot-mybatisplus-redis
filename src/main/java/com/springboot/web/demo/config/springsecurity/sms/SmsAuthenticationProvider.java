package com.springboot.web.demo.config.springsecurity.sms;

import com.springboot.web.demo.config.springsecurity.service.MyUserDetailsService;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author penghui
 * @date 2019/6/4 0004   9:37 
 *
 */
@Data
public class SmsAuthenticationProvider implements AuthenticationProvider {


    private MyUserDetailsService userDetailsService;

    /**
     * 通过UserDetailService 拿到用户信息 重新组装成一个已认证的token
     *
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken smsAuthenticationToken = (SmsAuthenticationToken) authentication;
        //获取用户信息
        UserDetails user = userDetailsService.loadUserByUsername((String) smsAuthenticationToken.getPrincipal());
        if(user == null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        //重新组装token信息
        SmsAuthenticationToken smsAuthenticationTokenRes = new SmsAuthenticationToken(user,user.getAuthorities());
        //请求信息组装进token
        smsAuthenticationTokenRes.setDetails(smsAuthenticationToken.getDetails());
        return smsAuthenticationTokenRes;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //判断传进来的对象是否是SmsAuthenticationToken这种类型
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
