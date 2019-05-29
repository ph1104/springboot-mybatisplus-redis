package com.springboot.web.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.web.demo.entity.LogVo;
import com.springboot.web.demo.entity.SysLog;
import com.springboot.web.demo.dao.SysLogDao;
import com.springboot.web.demo.service.SysLogService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (SysLog)表服务实现类
 *
 * @author makejava
 * @since 2019-05-06 14:49:27
 */
@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogDao,SysLog> implements SysLogService {
   
//    @Autowired
//    private SysLogDao sysLogDao;


    @Override
    public IPage<LogVo> getLogListById(Page page, Long userId) {
        return baseMapper.getLogListById(page,userId);
    }

    @Override
    public IPage<LogVo> getLogListObject(Page page, LogVo logVo) {
        return baseMapper.getLogListObject(page,logVo);
    }
}