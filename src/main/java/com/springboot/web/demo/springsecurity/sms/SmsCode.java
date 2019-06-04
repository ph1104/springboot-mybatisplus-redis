package com.springboot.web.demo.springsecurity.sms;

import com.springboot.web.demo.springsecurity.common.ValidateCode;

/**
 * @author penghui
 * @date 2019/6/3 000316:33
 */
public class SmsCode extends ValidateCode {

    public SmsCode(String code, int expireIn) {
        super(code, expireIn);
    }
}
