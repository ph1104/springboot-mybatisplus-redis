package com.springboot.web.demo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.web.demo.dao.SysDeptDao;
import com.springboot.web.demo.model.dto.DeptTree;
import com.springboot.web.demo.model.entity.SysDept;
import com.springboot.web.demo.model.entity.SysDeptRelation;
import com.springboot.web.demo.service.SysDeptRelationService;
import com.springboot.web.demo.service.SysDeptService;
import com.springboot.web.demo.util.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 部门管理(SysDept)表服务实现类
 *
 * @author penghui
 * @since 2019-06-17 10:43:29
 */
@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDept> implements SysDeptService {
   
    @Autowired
    private SysDeptRelationService sysDeptRelationService;


    /**
     * 添加部门信息
     * @param sysDept
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveDept(SysDept sysDept) {
        //保存部门信息
        this.save(sysDept);
        //保存部门关系信息
        sysDeptRelationService.saveSysDeptRelation(sysDept);
        return true;
    }


    /**
     * 构建部门树
     * @return
     */
    @Override
    public List<DeptTree> deptTree() {
        List<SysDept> sysDeptList = this.list();
        List<DeptTree> treeList = new ArrayList<>();
        for(int i = 0 ;i < sysDeptList.size() ; i++){
            SysDept sysDept = sysDeptList.get(i);
            DeptTree node = new DeptTree();
            node.setName(sysDept.getName());
            node.setId(sysDept.getDeptId());
            node.setParentId(sysDept.getParentId());
            treeList.add(node);
        }
        return TreeUtil.buildByLoop(treeList, 0);
    }


    /**
     * 更新部门信息
     * @param sysDept
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateDept(SysDept sysDept) {
        SysDept sysDeptOld = this.getById(sysDept.getDeptId());
        //更新部门信息
        this.updateById(sysDept);
        // 节点的父id发生改变则更新部门关系
        if(ObjectUtil.isNotNull(sysDept.getParentId()) && !sysDeptOld.getParentId().equals(sysDept.getParentId())){
            // 先删除原来的节点关系
            sysDeptRelationService.remove(new QueryWrapper<SysDeptRelation>()
                    .lambda()
                    .eq(SysDeptRelation::getDescendant,sysDeptOld.getDeptId()));

            //再插入新的节点关系
            sysDeptRelationService.saveSysDeptRelation(sysDept);
        }
        return true;
    }


    /**
     * 删除部门信息
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeDept(Integer id) {
        //级联删除
        List<Integer> ids = sysDeptRelationService.list(new QueryWrapper<SysDeptRelation>()
                .lambda()
                .eq(SysDeptRelation::getAncestor,id))
                .stream()
                .map(SysDeptRelation::getDescendant)
                .collect(Collectors.toList());
        this.removeByIds(ids);
        //删除部门关系表
        sysDeptRelationService.removeDeptRelation(id);
        return true;
    }
}