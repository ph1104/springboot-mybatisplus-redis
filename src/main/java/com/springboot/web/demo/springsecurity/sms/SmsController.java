package com.springboot.web.demo.springsecurity.sms;


import com.springboot.web.demo.constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author penghui
 * @date 2019/6/3 0003   16:33
 *
 */
@Slf4j
@RestController
public class SmsController {


    private SmsCodeGenerator smsCodeGenerator = new SmsCodeGenerator();
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private  RedisTemplate myRedisTemplate;  //自定义RedisTemplate


    /**
     * 短信验证码生成
     *
     *
     *   1、根据规则生成验证码
     *
     *   2、将验证码存入到session中
     *
     *   3、将验证码发送给用户
     */

    @GetMapping("/createSmsCode")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {

        String smsCode = smsCodeGenerator.generateCode(request);
        String mobile = ServletRequestUtils.getStringParameter(request,"mobile");
        //将短信验证码存入session
        //sessionStrategy.setAttribute(new ServletWebRequest(request),CommonConstant.SMS_SESSION_KEY,smsCode);
        //将短信验证码存入redis
        myRedisTemplate.opsForValue().set(SecurityConstants.SMS_REDIS_KEY+mobile,smsCode,3,TimeUnit.MINUTES);

        log.info("调用短信服务商发送短信：手机号{}，验证码{}",mobile,smsCode);
    }
}
