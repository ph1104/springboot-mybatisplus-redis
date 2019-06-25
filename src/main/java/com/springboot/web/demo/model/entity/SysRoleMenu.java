package com.springboot.web.demo.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 角色菜单表(SysRoleMenu)实体类
 *
 * @author penghui
 * @since 2019-06-17 10:44:49
 */
@Data
@ApiModel(value = "SysRoleMenu实体类",description = "SysRoleMenu")
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = -58468973374839823L;

    @ApiModelProperty(value = "角色ID")
    private Integer roleId;


    @ApiModelProperty(value = "菜单ID")
    private Integer menuId;


}