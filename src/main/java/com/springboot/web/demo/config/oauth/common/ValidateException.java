package com.springboot.web.demo.config.oauth.common;


import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常处理类
 * @author penghui
 * @date 2019/6/3 000314:01
 */
public class ValidateException extends AuthenticationException {

    public ValidateException(String msg){
        super(msg);
    }
}
