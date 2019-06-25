package com.springboot.web.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.web.demo.dao.SysDeptRelationDao;
import com.springboot.web.demo.model.entity.SysDept;
import com.springboot.web.demo.model.entity.SysDeptRelation;
import com.springboot.web.demo.service.SysDeptRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门关系表(SysDeptRelation)表服务实现类
 *
 * @author penghui
 * @since 2019-06-17 10:44:20
 */
@Service("sysDeptRelationService")
public class SysDeptRelationServiceImpl extends ServiceImpl<SysDeptRelationDao, SysDeptRelation> implements SysDeptRelationService {
   
    @Autowired
    private SysDeptRelationDao sysDeptRelationDao;


    /**
     * 新增部门关系
     * @param sysDept
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveSysDeptRelation(SysDept sysDept) {
        //查看新增节点的 父节点 是哪些节点的子节点
        List<SysDeptRelation> sysDeptRelationList = sysDeptRelationDao.selectList(new QueryWrapper<SysDeptRelation>()
                .lambda()
                .eq(SysDeptRelation::getDescendant,sysDept.getParentId()));
        //维护新增节点所属父节点的关系
        List<SysDeptRelation> sysDeptRelationListNew = new ArrayList<>();
        if(sysDeptRelationList != null && sysDeptRelationList.size() > 0){
            for(int i = 0 ;i < sysDeptRelationList.size() ; i++){
                SysDeptRelation sysDeptRelation = new SysDeptRelation();
                sysDeptRelation.setAncestor(sysDeptRelationList.get(i).getAncestor());
                sysDeptRelation.setDescendant(sysDept.getDeptId());
                sysDeptRelationListNew.add(sysDeptRelation);
            }
        }
        this.saveBatch(sysDeptRelationListNew);

        //自己也要维护到关系表中
        SysDeptRelation own = new SysDeptRelation();
        own.setDescendant(sysDept.getDeptId());
        own.setAncestor(sysDept.getDeptId());
        sysDeptRelationDao.insert(own);
        return true;
    }


    /**
     * 根据节点id删除部门关系
     * @param id
     * @return
     */
    @Override
    public Boolean removeDeptRelation(Integer id) {
        return sysDeptRelationDao.removeDeptRelation(id);
    }
}