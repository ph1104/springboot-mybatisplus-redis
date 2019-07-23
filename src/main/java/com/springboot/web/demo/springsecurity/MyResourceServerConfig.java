package com.springboot.web.demo.springsecurity;

import com.springboot.web.demo.constant.SecurityConstants;
import com.springboot.web.demo.springsecurity.handler.MyAuthenticationFailureHandler;
import com.springboot.web.demo.springsecurity.image.ImageCodeFilter;
import com.springboot.web.demo.springsecurity.sms.SmsAuthenticationSecurityConfig;
import com.springboot.web.demo.springsecurity.sms.SmsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * 资源服务器
 * @author penghui
 * @date 2019/6/4 0004   16:13
 *
 */
@Configuration
@EnableResourceServer
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * 用来处理密码的加密解密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Autowired
    private ImageCodeFilter imageCodeFilter;
    @Autowired
    private SmsFilter smsFilter;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureEventHandler;
    @Autowired
    private SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig;
    @Autowired
    private OAuth2WebSecurityExpressionHandler expressionHandler;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(smsFilter,UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(imageCodeFilter,UsernamePasswordAuthenticationFilter.class)  //将图片验证码过滤器加入到UsernamePassword过滤器之前
                .formLogin()     //表单登录的方式
//              .httpBasic()   //httpBasic的登录方式
                .loginPage("/index.html")      //自定义的登录页面
                //.successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureEventHandler)
                //.loginProcessingUrl(SecurityConstants.USERNAME_PASS_TOKEN_URL)  //表单用户名密码登录时处理的请求
                .and()
                .authorizeRequests()  //对请求做授权
                .antMatchers(SecurityConstants.MOBILE_TOKEN_URL,
                        "/index.html",
                        "/js/**",
                        "/createImageCode",
                        "/createSmsCode",

                        "/swagger-ui.html",
                        "/webjars/**",
                        "/resources/**",
                        "/swagger-resources/**",
                        "/v2/api-docs")
                .permitAll()
                //.antMatchers("/company/*").hasRole("ADMIN")
                .anyRequest()
                .access("@rbacService.hasPermission(request,authentication)")
                //.authenticated()      //都需要权限认证
                .and()
                .csrf().disable()   //跨站请求防护功能关闭
                .apply(smsAuthenticationSecurityConfig); //短信验证码的配置
    }



    //解决 .anyRequest().access()时  no bean resolver registered的问题
    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.expressionHandler(expressionHandler);
    }

}
