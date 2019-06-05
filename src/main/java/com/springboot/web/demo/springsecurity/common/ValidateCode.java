package com.springboot.web.demo.springsecurity.common;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author penghui
 *
 *
 * 验证码实体类
 */
@Data
public class ValidateCode implements Serializable{

    private static final long serialVersionUID = 2956318048733954070L;
    //验证码
    private String code;

    //过期时间
    private LocalDateTime expireTime;

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}
