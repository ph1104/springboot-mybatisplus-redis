package com.springboot.web.demo.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration

public class MybatisPlusConfig {

//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        //paginationInterceptor.setLocalPage(true);// 开启 PageHelper 的支持
//        List<ISqlParser> sqlParserList = new ArrayList<>();
//
//        //TenantSqlParser tenantSqlParser = new TenantSqlParser();
//        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
//
//        Map<String, ITableNameHandler> map = new HashMap<>();
//        map.put("test.company",new MyTableNameHandler());
//        dynamicTableNameParser.setTableNameHandlerMap(map);
//        sqlParserList.add(dynamicTableNameParser);
//        paginationInterceptor.setSqlParserList(sqlParserList);
////        paginationInterceptor.setSqlParserFilter(metaObject -> {
////            MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
////            // 过滤自定义查询此时无租户信息约束【 麻花藤 】出现
////            if ("com.baomidou.springboot.mapper.UserMapper.selectListBySQL".equals(ms.getId())) {
////                return true;
////            }
////            return false;
////        });
//        return paginationInterceptor;
//    }


    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
