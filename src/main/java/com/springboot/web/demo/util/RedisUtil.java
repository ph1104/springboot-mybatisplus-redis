package com.springboot.web.demo.util;

import com.springboot.web.demo.constant.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;



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
        myRedisTemplate.opsForValue().set(SecurityConstants.SMS_REDIS_KEY,code);
    }

    /**
     * 获取验证码
     * @param code
     */
    public static void getCode(String code){
        myRedisTemplate.opsForValue().set(SecurityConstants.SMS_REDIS_KEY,code);
    }


    /**
     * 移除验证码
     * @param code
     */
    public static void removeCode(String code){
        myRedisTemplate.opsForValue().set(SecurityConstants.SMS_REDIS_KEY,code);
    }


}
