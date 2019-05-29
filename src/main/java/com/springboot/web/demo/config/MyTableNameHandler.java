package com.springboot.web.demo.config;

import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

@Slf4j
public class MyTableNameHandler implements ITableNameHandler {
    @Override
    public String process(MetaObject metaObject, String sql, String tableName) {
        log.info("sql:{},tablename:{}",sql,tableName);

        String dynamicTableName = dynamicTableName(metaObject, sql, tableName);
        if (null != dynamicTableName && !dynamicTableName.equalsIgnoreCase(tableName)) {
            return sql.replaceAll(tableName, dynamicTableName);
        }
        return sql;
        //return null;
    }

    @Override
    public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
        log.info("sql:{},tablename:{}",sql,tableName);
        tableName = tableName + "_12";
        return tableName;
    }
}
