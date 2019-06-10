package com.springboot.web.demo.springsecurity;


import com.springboot.web.demo.springsecurity.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 认证服务器
 *
 * @author penghui
 * @date 2019/6/4 0004   16:15
 *
 */
@Configuration
@EnableAuthorizationServer
public class MyAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private MyUserDetailsService myUserDetailsService;

    //必须这么写，不然会报错
    private final AuthenticationManager authenticationManager;
    private final RedisConnectionFactory redisConnectionFactory;


    @Autowired
    private TokenStore jwtTokenStore;

    private final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();

    /**
     * SpringBoot 2.X 版本中Spring Security 4.x -> 5.x
     *
     * AuthenticationManager不可再直接注入，需要配置一个配置类继承WebSecurityConfigurerAdapter，重写父类的方法（详见SpringSecurityConfig）
     *
     */
//    @Autowired
//    private AuthenticationManager authenticationManager;

    public MyAuthorizationServerConfig(AuthenticationManager authenticationManager, RedisConnectionFactory redisConnectionFactory) {
        this.authenticationManager = authenticationManager;
        this.redisConnectionFactory = redisConnectionFactory;
    }


    @Bean
    public TokenStore tokenStore() {

        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        return tokenStore;


    }





    /**
     *
     * 配置入口点（继承AuthorizationServerConfigurerAdapter后需要显示配置，不继承的情况下默认有）
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.tokenStore(tokenStore())   //将token存放到redis
                 .accessTokenConverter(jwtAccessTokenConverter)

                .authenticationManager(authenticationManager)
                .userDetailsService(myUserDetailsService);
    }

    /**
     * 配置请求的客户端的相关信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("phId")   //客户端id
                .secret("phSecret")      //客户端密码
                .accessTokenValiditySeconds(7200)  //token失效时间
                .authorizedGrantTypes("authorization code","password","refresh_token")   //允许的授权类型
                .scopes("all");      //权限

//        配置多个客户端
//               .and()
//                .withClient("phId")   //客户端id
//                .secret("phSecret")      //客户端密码
//                .accessTokenValiditySeconds(7200)  //token失效时间
//                .authorizedGrantTypes("authorization code","password","refresh_token")   //允许的授权类型
//                .scopes("all");


    }
}
