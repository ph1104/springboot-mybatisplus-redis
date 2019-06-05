package com.springboot.web.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import static com.springboot.web.demo.springsecurity.common.CommonConstant.SMS_REDIS_KEY;

/**
 * @author Administrator
 * @date 2019/6/5 00059:44
 */
public class RedisUtil {

    @Autowired
    private static RedisTemplate myRedisTemplate;  //自定义RedisTemplate



    /**
     * 保存验证码
     * @param code
     */
    public static void saveCode(String code){
        myRedisTemplate.opsForValue().set(SMS_REDIS_KEY,code);
    }

    /**
     * 获取验证码
     * @param code
     */
    public static void getCode(String code){
        myRedisTemplate.opsForValue().set(SMS_REDIS_KEY,code);
    }


    /**
     * 移除验证码
     * @param code
     */
    public static void removeCode(String code){
        myRedisTemplate.opsForValue().set(SMS_REDIS_KEY,code);
    }


}
