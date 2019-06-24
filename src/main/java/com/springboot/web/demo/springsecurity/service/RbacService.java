package com.springboot.web.demo.springsecurity.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface RbacService {
    Boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
