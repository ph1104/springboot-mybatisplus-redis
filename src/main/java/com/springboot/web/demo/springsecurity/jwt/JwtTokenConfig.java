package com.springboot.web.demo.springsecurity.jwt;

import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author Administrator
 * @date 2019/6/6 000610:08
 */
//@Configuration
public class JwtTokenConfig {

//    @Bean
//    public TokenStore jwtTokenStore(){
//        return new JwtTokenStore(jwtAccessTokenConverter());
//    }

    //@Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("penghui");
        return jwtAccessTokenConverter;
    }
}
