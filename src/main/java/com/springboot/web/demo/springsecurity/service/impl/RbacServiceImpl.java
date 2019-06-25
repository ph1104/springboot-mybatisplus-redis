package com.springboot.web.demo.springsecurity.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.springboot.web.demo.model.entity.SysMenu;
import com.springboot.web.demo.model.entity.SysRole;
import com.springboot.web.demo.model.vo.UserVO;
import com.springboot.web.demo.service.SysMenuService;
import com.springboot.web.demo.service.SysUserService;
import com.springboot.web.demo.springsecurity.entity.MyUser;
import com.springboot.web.demo.springsecurity.service.RbacService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component("rbacService")
public class RbacServiceImpl implements RbacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public Boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        //排除UserDetail是Anonymous的情况
        if(principal instanceof UserDetails){
            Integer id = ((MyUser)principal).getId();
            //根据用户名查询角色列表
            UserVO userVO = sysUserService.getUserInfoById(id);
            List<Integer> roleIds  = userVO.getRoleList()
                    .stream()
                    .map(SysRole::getRoleId)
                    .collect(Collectors.toList());
            //根据角色id查询用户所有权限urls
           List<SysMenu> menuList = sysMenuService.listPermissionsByRoleIds(CollUtil.join(roleIds, ","));
            for(SysMenu menu : menuList){
                if(antPathMatcher.match(menu.getUrl()==null?"":menu.getUrl(), request.getRequestURI())){
                    if(request.getMethod().equals(menu.getMethod())){
                        hasPermission = true ;
                        break ;
                    }
                }
            }
        }
        log.info("是否有权限：{}",hasPermission);
        return hasPermission;
    }
}
