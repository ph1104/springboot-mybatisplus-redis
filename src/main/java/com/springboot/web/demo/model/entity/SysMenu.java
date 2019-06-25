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
 * 菜单权限表(SysMenu)实体类
 *
 * @author penghui
 * @since 2019-06-18 16:50:05
 */
@Data
@ApiModel(value = "SysMenu实体类",description = "SysMenu")
public class SysMenu implements Serializable {
    private static final long serialVersionUID = -34980586256812725L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "菜单ID")
    private Integer menuId;


    @ApiModelProperty(value = "菜单名称")
    private String name;


    @ApiModelProperty(value = "菜单权限标识")
    private String permission;


    @ApiModelProperty(value = "前端URL")
    private String path;


    @ApiModelProperty(value = "请求链接")
    private String url;


    @ApiModelProperty(value = "请求方法")
    private String method;


    @ApiModelProperty(value = "父菜单ID")
    private Integer parentId;


    @ApiModelProperty(value = "图标")
    private String icon;


    @ApiModelProperty(value = "VUE页面")
    private String component;


    @ApiModelProperty(value = "排序值")
    private Integer sort;


    @ApiModelProperty(value = "0-开启，1- 关闭")
    private String keepAlive;


    @ApiModelProperty(value = "菜单类型 （0菜单 1按钮）")
    private String type;


    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


    @TableLogic
    @ApiModelProperty(value = "逻辑删除标记(0 正常  -1 删除)")
    private String delFlag;


}