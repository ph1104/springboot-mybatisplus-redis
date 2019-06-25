package com.springboot.web.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;


@Slf4j
//@Component
public class MybatisPlusFillConfig  implements MetaObjectHandler {

    private int id = 10001;

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("-------------------------------start insert fill -------------------------------");
        //this.setInsertFieldValByName("companyId",id++, metaObject);
        //this.setInsertFieldValByName("companyName","电网", metaObject);
        //this.setInsertFieldValByName("areaId",90001, metaObject);

        this.setInsertFieldValByName("id",id++, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("-------------------------------start update fill -------------------------------");
        this.setUpdateFieldValByName("updateTime", new Date() ,metaObject);
    }
}
