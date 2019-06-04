package com.springboot.web.demo.config.oauth.sms;

import com.springboot.web.demo.config.oauth.common.ValidateCode;

import java.time.LocalDateTime;

/**
 * @author penghui
 * @date 2019/6/3 000316:33
 */
public class SmsCode extends ValidateCode {

    public SmsCode(String code, int expireIn) {
        super(code, expireIn);
    }
}
