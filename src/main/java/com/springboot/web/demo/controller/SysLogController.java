package com.springboot.web.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.web.demo.entity.Company;
import com.springboot.web.demo.entity.LogVo;
import com.springboot.web.demo.entity.SysLog;
import com.springboot.web.demo.service.SysLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (SysLog)表控制层
 *
 * @author makejava
 * @since 2019-05-06 14:49:27
 */
@RestController
@RequestMapping("sysLog")
@Api(tags = "日志controller")
public class SysLogController {
    /**
     * 服务对象
     */
    @Autowired
    private SysLogService sysLogService;

    @GetMapping("getLogListById")
    @ApiOperation("关联查询单个参数分页")
    public IPage<LogVo> getLogListById(@RequestParam Long current, @RequestParam Long size,@RequestParam Long userId){
        return sysLogService.getLogListById(new Page(current,size),userId);
    }

    @GetMapping("getLogListObject")
    @ApiOperation("关联查询对象参数分页")
    public IPage<LogVo> getLogListObject(@RequestParam Long current, @RequestParam Long size){
        LogVo logVo = new LogVo();
        logVo.setUsername("张三");
        return sysLogService.getLogListObject(new Page(current,size),logVo);
    }

    /**
     * 新增公司信息
     * @param sysLog
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增公司信息")
    public Boolean saveCompany(@RequestBody SysLog sysLog) {
        return sysLogService.save(sysLog);
    }




}