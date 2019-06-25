package com.springboot.web.demo.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 角色与部门对应关系(SysRoleDept)实体类
 *
 * @author penghui
 * @since 2019-06-17 10:44:43
 */
@Data
@ApiModel(value = "SysRoleDept实体类",description = "SysRoleDept")
public class SysRoleDept implements Serializable {
    private static final long serialVersionUID = 185108553560492871L;

    
    private Integer id;


    @ApiModelProperty(value = "角色ID")
    private Integer roleId;


    @ApiModelProperty(value = "部门ID")
    private Integer deptId;


}