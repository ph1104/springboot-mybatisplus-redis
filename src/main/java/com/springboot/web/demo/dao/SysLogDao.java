package com.springboot.web.demo.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.web.demo.entity.LogVo;
import com.springboot.web.demo.entity.SysLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (SysLog)表数据库访问层
 *
 * @author makejava
 * @since 2019-05-06 14:49:27
 */
public interface SysLogDao extends BaseMapper<SysLog>{

    IPage<LogVo> getLogListById(Page page, @Param("userId")Long userId);

    IPage<LogVo> getLogListObject(Page page, @Param("logVo")LogVo logVo);
}