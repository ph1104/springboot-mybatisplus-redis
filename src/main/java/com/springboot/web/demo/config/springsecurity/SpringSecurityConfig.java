package com.springboot.web.demo.config.springsecurity;

import com.springboot.web.demo.config.springsecurity.handler.MyAuthenticationFailureHandler;
import com.springboot.web.demo.config.springsecurity.handler.MyAuthenticationSuccessHandler;
import com.springboot.web.demo.config.springsecurity.image.ImageCodeFilter;
import com.springboot.web.demo.config.springsecurity.sms.SmsAuthenticationSecurityConfig;
import com.springboot.web.demo.config.springsecurity.sms.SmsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 用来处理密码的加密解密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }


    /**
     * SpringSecurity:    一系列的过滤器链
     *
     * SecurityContextPersitenceFilter----->
     * UsernamePasswordAuthenticationFilter  ------>
     * BasicAuthenticationFilter  ------->
     * ExcepionTranslationFilter ------->
     * FilterSecurityInterceptor
     *
     *
     * SecurityContextPersitenceFilter: 请求进入的时候检查session是否有认证信息，有就放入进程中，请求返回的时候，检查进程中是否有认证信息，有就放入session
     * UsernamePasswordAuthenticationFilter：基于用户名密码的认证，校验用户名和密码等信息
     * BasicAuthenticationFilter：基于HttpBasic的认证，校验请求头中是否有basic认证信息
     * ExceptionTranslationFilter ：处理后面的拦截器抛出来的异常信息
     * FilterSecurityInterceptor: 处理授权信息，即.authorizeRequests()方法后面的一些处理，例如·
     *                 .authorizeRequests()               //对请求做授权
     *                 .anyRequest()                      //任何请求
     *                 .authenticated()                   //都需要身份认证
     *
     */


    @Autowired
    private ImageCodeFilter imageCodeFilter;
    @Autowired
    private SmsFilter smsFilter;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(smsFilter,UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(imageCodeFilter,UsernamePasswordAuthenticationFilter.class)  //将图片验证码过滤器加入到UsernamePassword过滤器之前
                .formLogin()     //表单登录的方式
//              .httpBasic()   //httpBasic的登录方式
                .loginPage("/index.html")      //自定义的登录页面
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .loginProcessingUrl("/authentication/form")  //表单登录时处理的请求

                .and()
                .authorizeRequests()  //对请求做授权
                .antMatchers("/index.html",
                        "/createImageCode",
                        "/createSmsCode",
                        "/authentication/mobile").permitAll()
                .anyRequest()          //任何请求
                .authenticated()      //都需要权限认证
                .and()
                .csrf().disable()   //跨站请求防护功能关闭
                .apply(smsAuthenticationSecurityConfig);
    }
}
