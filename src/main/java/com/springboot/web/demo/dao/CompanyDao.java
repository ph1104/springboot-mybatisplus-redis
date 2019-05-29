package com.springboot.web.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.web.demo.entity.Company;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.reflection.wrapper.BaseWrapper;

import java.util.List;

/**
 * (Company)表数据库访问层
 *
 * @author makejava
 * @since 2019-04-25 16:58:34
 */
public interface CompanyDao extends BaseMapper<Company> {

}