package com.springboot.web.demo.entity;

import java.io.Serializable;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * (SysUser)实体类
 *
 * @author makejava
 * @since 2019-06-03 14:43:05
 */
@Data
@ApiModel(value = "SysUser实体类",description = "SysUser")
public class SysUser implements Serializable {
    private static final long serialVersionUID = -63222166467519626L;

    @ApiModelProperty(value = "主键ID")
    private Long id;


    @ApiModelProperty(value = "姓名")
    private String name;


    @ApiModelProperty(value = "密码")
    private String password;


    @ApiModelProperty(value = "年龄")
    private Integer age;


    @ApiModelProperty(value = "手机号")
    private String phoneNumber;


}