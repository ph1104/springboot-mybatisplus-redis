package com.springboot.web.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.web.demo.dao.SysRoleDeptDao;
import com.springboot.web.demo.model.entity.SysRoleDept;
import com.springboot.web.demo.service.SysRoleDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 角色与部门对应关系(SysRoleDept)表服务实现类
 *
 * @author penghui
 * @since 2019-06-17 10:44:43
 */
@Service("sysRoleDeptService")
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptDao, SysRoleDept> implements SysRoleDeptService {
   
    @Autowired
    private SysRoleDeptDao sysRoleDeptDao;

   
}