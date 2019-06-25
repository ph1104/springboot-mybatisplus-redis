package com.springboot.web.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.web.demo.model.dto.DeptTree;
import com.springboot.web.demo.model.entity.SysDept;

import java.util.List;


/**
 * 部门管理(SysDept)表服务接口
 *
 * @author penghui
 * @since 2019-06-17 10:43:29
 */
public interface SysDeptService extends IService<SysDept>{


    Boolean saveDept(SysDept sysDept);

    List<DeptTree> deptTree();

    Boolean updateDept(SysDept sysDept);

    Boolean removeDept(Integer id);


}