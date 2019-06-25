package com.springboot.web.demo.constant;

public interface SecurityConstants {

    /**
     * 用户名密码登录URL
     */
    String USERNAME_PASS_TOKEN_URL = "/authentication/form";

    /**
     * 图形验证码key
     */
    String IMAGE_SESSION_KEY = "IMAGE_SESSION_KEY_CODE";


    /**
     * 短信验证码key
     */
    String SMS_REDIS_KEY = "code:sms:";

}
