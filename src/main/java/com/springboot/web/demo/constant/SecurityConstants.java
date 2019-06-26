package com.springboot.web.demo.constant;

public interface SecurityConstants {

    /**
     * 用户名密码登录URL
     */
    String USERNAME_PASS_TOKEN_URL = "/authentication/form";

    /**
     * 手机号登录URL
     */
    String MOBILE_TOKEN_URL = "/authentication/mobile";

    /**
     * 短信验证码key
     */
    String SMS_REDIS_KEY = "code:sms:";
    /**
     * 图形验证码key
     */
    String IMAGE_REDIS_KEY = "code:image:";
}
