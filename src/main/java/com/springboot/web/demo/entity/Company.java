package com.springboot.web.demo.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * (Company)实体类
 *
 * @author makejava
 * @since 2019-04-26 10:14:43
 */
@Data
@ApiModel(value = "Company实体类",description = "Company")
@TableName("test.company")
public class Company implements Serializable {
    private static final long serialVersionUID = 930274131250518300L;


    //@TableField(value = "id", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "公司编码")
    @TableId(type = IdType.ID_WORKER)
    private Integer id;


    @ApiModelProperty(value = "公司名称         ")
    //@TableField(value = "company_name", fill = FieldFill.INSERT)
    private String companyName;


    @ApiModelProperty(value = "税号         ")
    private String taxNo;


    @ApiModelProperty(value = "银行账户         ")
    private String bankNo;


    @ApiModelProperty(value = "开户银行         ")
    private String bank;


    @ApiModelProperty(value = "公司地址         ")
    private String companyAddress;


    @ApiModelProperty(value = "公司电话         ")
    private String companyTel;

    //@TableField(value = "area_id", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "公司所在地址         ")
    private Integer areaId;


    @ApiModelProperty(value = "创建时间         ")
    private Date craeteTime;

    @TableField(value = "update_time" ,fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}