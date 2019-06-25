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
 * 系统角色表(SysRole)实体类
 *
 * @author penghui
 * @since 2019-06-19 13:43:54
 */
@Data
@ApiModel(value = "SysRole实体类",description = "SysRole")
public class SysRole implements Serializable {
    private static final long serialVersionUID = 115965152153330200L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "角色id")
    private Integer roleId;


    @ApiModelProperty(value = "角色名称")
    private String roleName;


    @ApiModelProperty(value = "角色code")
    private String roleCode;


    @ApiModelProperty(value = "角色描述")
    private String roleDesc;


    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


    @TableLogic
    @ApiModelProperty(value = "删除标识（0 正常,-1 删除）")
    private String delFlag;


}