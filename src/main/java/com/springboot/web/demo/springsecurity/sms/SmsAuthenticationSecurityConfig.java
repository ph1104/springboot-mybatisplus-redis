package com.springboot.web.demo.springsecurity.sms;

import com.springboot.web.demo.springsecurity.handler.MyAuthenticationFailureHandler;
import com.springboot.web.demo.springsecurity.handler.MyAuthenticationSuccessHandler;
import com.springboot.web.demo.springsecurity.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 组装自定义的SmsAuthenticationProvider  SmsAuthenticationFilter
 *
 * @author penghui
 * @date 2019/6/4 0004   10:16
 *
 */
@Component
public class SmsAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private MyAuthenticationSuccessHandler successHandler;
    @Autowired
    private MyAuthenticationFailureHandler failureHandler;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
        //smsAuthenticationFilter调用AuthenticationManager
        smsAuthenticationFilter.setAuthenticationManager(httpSecurity.getSharedObject(AuthenticationManager.class));
        //设置自定义SmsAuthenticationFilter的登录成功处理器
        smsAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
        //设置自定义SmsAuthenticationFilter的登录失败处理器
        smsAuthenticationFilter.setAuthenticationFailureHandler(failureHandler);

        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
        smsAuthenticationProvider.setUserDetailsService(myUserDetailsService);

        httpSecurity.authenticationProvider(smsAuthenticationProvider)  //将自定义SmsAuthenticationProvider的加入到AuthenticationProvider
                    .addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
