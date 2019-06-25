package com.springboot.web.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.web.demo.model.entity.SysDeptRelation;


/**
 * 部门关系表(SysDeptRelation)表数据库访问层
 *
 * @author penghui
 * @since 2019-06-17 10:44:20
 */
public interface SysDeptRelationDao extends BaseMapper<SysDeptRelation>{
    Boolean removeDeptRelation(Integer id);
}