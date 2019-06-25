package com.springboot.web.demo.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.web.demo.constant.CommonConstants;
import com.springboot.web.demo.dao.SysUserDao;
import com.springboot.web.demo.model.entity.SysUser;
import com.springboot.web.demo.model.entity.SysUserRole;
import com.springboot.web.demo.model.vo.UserVO;
import com.springboot.web.demo.service.SysUserRoleService;
import com.springboot.web.demo.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户表(SysUser)表服务实现类
 *
 * @author penghui
 * @since 2019-06-17 10:46:27
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleService sysUserRoleService;


    /**
     * 通过ID查询用户信息
     * @param id
     * @return
     */
    @Override
    public UserVO getUserInfoById(Integer id) {
        return sysUserDao.getUserInfoById(id);
    }

    /**
     * 保存用户信息
     * @param userVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveUser(UserVO userVO) {
        //插入用户信息
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userVO,sysUser);
        sysUser.setDelFlag(CommonConstants.STATUS_NORMAL);
        sysUser.setPassword(passwordEncoder.encode(userVO.getPassword()));
        sysUserDao.insert(sysUser);
        //插入用户角色信息
        userVO.setUserId(sysUser.getUserId());
        sysUserRoleService.saveUserRole(userVO);
        return true;
    }


    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeUser(Integer id) {
        //删除用户信息
        this.removeById(id);
        //删除用户角色信息
        sysUserRoleService.remove(new QueryWrapper<SysUserRole>()
                .lambda()
                .eq(SysUserRole::getUserId,id));
        return true;
    }


    /**
     * 修改用户信息
     * @param userVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateUser(UserVO userVO) {
        //修改用户信息
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userVO,sysUser);
        if(StrUtil.isNotBlank(userVO.getPassword())){
            sysUser.setPassword(passwordEncoder.encode(userVO.getPassword()));
        }
        sysUserDao.updateById(sysUser);
        //修改用户角色信息
        if(userVO.getRoleList() != null & userVO.getRoleList().size()>0){
            //删除用户角色信息
            sysUserRoleService.remove(new QueryWrapper<SysUserRole>()
                    .lambda()
                    .eq(SysUserRole::getUserId,userVO.getUserId()));
            //新增用户角色信息
            sysUserRoleService.saveUserRole(userVO);
        }
        return true;
    }
}