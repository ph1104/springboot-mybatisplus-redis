package com.springboot.web.demo.springsecurity.service.impl;

import com.springboot.web.demo.springsecurity.service.RbacService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component("rbacService")
public class RbacServiceImpl implements RbacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        //排除UserDetail是Anonymous的情况
        if(principal instanceof UserDetails){
            String username = ((UserDetails)principal).getUsername();
            //查询用户所有权限urls
            Set<String> urls = new HashSet<>();
            urls.add("/company/*");
            for(String url : urls){
                if(antPathMatcher.match(url==null?"":url, request.getRequestURI())){
                    hasPermission = true ;
                    break ;
                }
            }
        }
        log.info("是否有权限：{}",hasPermission);
        return hasPermission;
    }
}
