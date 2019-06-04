package com.springboot.web.demo.config.oauth.sms;


import com.springboot.web.demo.config.oauth.common.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        SmsCode smsCode = smsCodeGenerator.generateCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request),CommonConstant.SMS_SESSION_KEY,smsCode);
        String mobile = ServletRequestUtils.getStringParameter(request,"mobile");
        log.info("调用短信服务商发送短信：手机号{}，验证码{},过期时间{}",mobile,smsCode.getCode(),smsCode.getExpireTime());
    }
}
