package com.springboot.web.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.web.demo.model.entity.SysDept;
import com.springboot.web.demo.model.entity.SysDeptRelation;


/**
 * 部门关系表(SysDeptRelation)表服务接口
 *
 * @author penghui
 * @since 2019-06-17 10:44:20
 */
public interface SysDeptRelationService extends IService<SysDeptRelation>{
    Boolean saveSysDeptRelation(SysDept sysDept);

    Boolean removeDeptRelation(Integer id);
}