package com.springboot.web.demo.springsecurity.jwt;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author penghui
 * @date 2019/6/6 0006   8:47
 *
 */
//@Configuration
public class MyTokenStore{


    //@Bean
    public TokenStore redisTokenStore(RedisConnectionFactory redisConnectionFactory){
        return new RedisTokenStore(redisConnectionFactory);
    }

}
