package com.springboot.web.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.web.demo.entity.LogVo;
import com.springboot.web.demo.entity.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;


/**
 * (SysLog)表服务接口
 *
 * @author makejava
 * @since 2019-05-06 14:49:27
 */
public interface SysLogService extends IService<SysLog>{

    IPage<LogVo> getLogListById(Page page, Long userId);

    IPage<LogVo> getLogListObject(Page page,LogVo logVo);
}