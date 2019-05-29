package com.springboot.web.demo.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * (SysLog)实体类
 *
 * @author makejava
 * @since 2019-05-06 14:49:25
 */
@Data
@ApiModel(value = "SysLog实体类",description = "SysLog")
public class SysLog implements Serializable {
    private static final long serialVersionUID = 968316586038806049L;

    @TableId(type = IdType.ID_WORKER_STR)
    //@TableField(fill = FieldFill.INSERT)
    private String id;


    @ApiModelProperty(value = "日志内容")
    private String content;


    @ApiModelProperty(value = "操作人")
    private Long userId;


    @ApiModelProperty(value = "操作时间")
    private Date operateTime;


}