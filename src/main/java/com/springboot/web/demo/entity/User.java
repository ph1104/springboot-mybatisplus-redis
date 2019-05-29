package com.springboot.web.demo.entity;

import java.io.Serializable;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * (User)实体类
 *
 * @author makejava
 * @since 2019-05-28 11:16:34
 */
@Data
@ApiModel(value = "User实体类",description = "User")
public class User implements Serializable {
    private static final long serialVersionUID = -93679815599976631L;

    @ApiModelProperty(value = "主键ID")
    private Long id;


    @ApiModelProperty(value = "姓名")
    private String name;


    @ApiModelProperty(value = "年龄")
    private Integer age;


    @ApiModelProperty(value = "邮箱")
    private String email;


    @ApiModelProperty(value = "手机号")
    private String phoneNumber;


}