package com.springboot.web.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
public class MyRedisConfig {



    /**
     * 自定义序列化器
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> myRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        template.setDefaultSerializer(genericJackson2JsonRedisSerializer);
        return template;
    }


//    自定义缓存管理器 springbooot 1.x版本
//    @Bean
//    public RedisCacheManager myRedisCacheManager(RedisTemplate<Object, User> myRedisTemplate){
//        RedisCacheManager redisCacheManager = new RedisCacheManager(myRedisTemplate);
//        return redisCacheManager;
//
//    }




    /**
     * 自定义缓存管理器   springbooot 2.x版本
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisCacheManager myCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        //设置序列化器
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        //包装成SerializationPair类型
        RedisSerializationContext.SerializationPair serializationPair = RedisSerializationContext.SerializationPair.fromSerializer(genericJackson2JsonRedisSerializer);
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(serializationPair);
        //RedisCacheManager 生成器创建
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(redisCacheConfiguration);

        return builder.build();
    }
}
