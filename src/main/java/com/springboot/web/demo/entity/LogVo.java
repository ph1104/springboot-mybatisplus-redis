package com.springboot.web.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "LogVo实体类",description = "LogVo")
public class LogVo implements Serializable {
    private static final long serialVersionUID = 968316586038806049L;


    private String id;


    @ApiModelProperty(value = "日志内容")
    private String content;


    @ApiModelProperty(value = "操作人")
    private String username;


    @ApiModelProperty(value = "操作时间")
    private Date operateTime;


}