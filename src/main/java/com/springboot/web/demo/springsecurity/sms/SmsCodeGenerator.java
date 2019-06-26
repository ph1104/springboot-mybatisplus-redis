package com.springboot.web.demo.springsecurity.sms;

import org.apache.commons.lang.RandomStringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 短信验证码生成规则
 * @author penghui
 * @date 2019/6/3 000316:43
 */
public class SmsCodeGenerator {

    public String generateCode(HttpServletRequest request) {
        String code = RandomStringUtils.randomNumeric(6);
        return code;
    }
}
