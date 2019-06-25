package com.springboot.web.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.web.demo.dao.SysUserRoleDao;
import com.springboot.web.demo.model.entity.SysUserRole;
import com.springboot.web.demo.model.vo.UserVO;
import com.springboot.web.demo.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色表(SysUserRole)表服务实现类
 *
 * @author penghui
 * @since 2019-06-17 10:44:58
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {


    /**
     * 根据用户信息新增用户角色信息
     * @param userVO
     * @return
     */
    @Override
    public Boolean saveUserRole(UserVO userVO) {
        List<SysUserRole> userRoleList = userVO.getRoleList().stream().map(role -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(userVO.getUserId());
            sysUserRole.setRoleId(role.getRoleId());
            return sysUserRole;
        }).collect(Collectors.toList());
        this.saveBatch(userRoleList);
        return true;
    }
}