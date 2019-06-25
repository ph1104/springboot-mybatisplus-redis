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
 * 部门管理(SysDept)实体类
 *
 * @author penghui
 * @since 2019-06-17 10:43:29
 */
@Data
@ApiModel(value = "SysDept实体类",description = "SysDept")
public class SysDept implements Serializable {
    private static final long serialVersionUID = -66475806721033378L;


    @TableId(value = "dept_id",type = IdType.AUTO)
    private Integer deptId;


    @ApiModelProperty(value = "部门名称")
    private String name;


    @ApiModelProperty(value = "排序")
    private Integer sort;


    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    @ApiModelProperty(value = "修改时间")
    private Date updateTime;


    @TableLogic
    @ApiModelProperty(value = "是否删除  -1：已删除  0：正常")
    private String delFlag;


    
    private Integer parentId;


}