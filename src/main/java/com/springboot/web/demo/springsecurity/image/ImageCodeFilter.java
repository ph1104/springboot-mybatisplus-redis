package com.springboot.web.demo.springsecurity.image;

import cn.hutool.core.util.StrUtil;
import com.springboot.web.demo.constant.SecurityConstants;
import com.springboot.web.demo.springsecurity.common.ValidateException;
import com.springboot.web.demo.springsecurity.handler.MyAuthenticationFailureHandler;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图形验证码过滤器
 * @author penghui
 * @date 2019/6/3 0003 11:38
 *
 */
@Component
public class ImageCodeFilter extends OncePerRequestFilter {

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     *
     * 检验输入的验证码是否匹配

     *  将ImageCodeFilter加入到SpringSecurity的过滤器链中
     *
     *  在UsernamePasswordAuthenticationFilter之前进行校验:
     *     1》匹配成功则接着调用后续的过滤器
     *     2》匹配失败则抛出异常信息（需要有异常处理器处理异常）
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            //登录请求的时候才进行验证码校验
            if(StringUtils.equals(SecurityConstants.USERNAME_PASS_TOKEN_URL,request.getRequestURI())
                    && StringUtils.equalsIgnoreCase("post",request.getMethod()) ){
                validate(new ServletWebRequest(request));
            }
        }catch(ValidateException e){
            //校验失败，抛出异常信息,调用失败处理
            myAuthenticationFailureHandler.onAuthenticationFailure(request,response,e);
            return;
        }
        filterChain.doFilter(request,response);
    }




    /**
     *
     * 校验处理
     */
    public void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException,ValidateException {
        //请求中的验证码
        String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"imageCode");
         //存在缓存中的验证码
        String codeInCache = stringRedisTemplate.opsForValue().get(SecurityConstants.IMAGE_REDIS_KEY + codeInRequest);
        if(StringUtils.isBlank(codeInRequest)){
            throw new ValidateException("请输入验证码");
        }
        if(StrUtil.isBlank(codeInCache)){
            throw new ValidateException("验证码不存在");
        }
        if(!StringUtils.equals(codeInCache,codeInRequest)){
            throw new ValidateException("验证码不匹配");
        }
        //将缓存中的验证码移除
        stringRedisTemplate.delete(SecurityConstants.IMAGE_REDIS_KEY + codeInRequest);
    }
}
