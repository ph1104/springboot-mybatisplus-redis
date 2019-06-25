package com.springboot.web.demo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户表(SysUser)实体类
 *
 * @author penghui
 * @since 2019-06-17 10:46:26
 */
@Data
@ApiModel(value = "SysUser实体类",description = "SysUser")
public class SysUser implements Serializable {
    private static final long serialVersionUID = -45925874187467993L;

    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer userId;


    @ApiModelProperty(value = "用户名")
    private String username;


    
    private String password;


    @ApiModelProperty(value = "盐")
    private String salt;


    @ApiModelProperty(value = "简介")
    private String phone;


    @ApiModelProperty(value = "头像")
    private String avatar;


    @ApiModelProperty(value = "部门ID")
    private Integer deptId;


    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    @ApiModelProperty(value = "修改时间")
    private Date updateTime;


    @ApiModelProperty(value = "0-正常，9-锁定")
    private String lockFlag;


    @TableLogic
    @ApiModelProperty(value = "0 正常  -1 删除")
    private String delFlag;


    @ApiModelProperty(value = "微信openid")
    private String wxOpenid;


    @ApiModelProperty(value = "QQ openid")
    private String qqOpenid;


}