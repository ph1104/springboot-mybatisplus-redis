package com.springboot.web.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.web.demo.dao.UserDao;
import com.springboot.web.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    UserDao userDao;

    @Autowired
    StringRedisTemplate stringRedisTemplate;  //继承自 RedisTemplate<String, String>，操作的K、V都是字符串

    @Autowired
    RedisTemplate redisTemplate;  //K,V都是Object

    @Autowired
    RedisTemplate myRedisTemplate;  //自定义RedisTemplate

//    @Test
//    public void contextLoads() {
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("192.168.146.128",9200,"http")));
//    }



    @Test
    public void stringRedisTemplateTest(){
        log.info("存字符串：");
        stringRedisTemplate.opsForValue().set("k1","v1");
        log.info("取字符串：{}",stringRedisTemplate.opsForValue().get("k1"));

        log.info("存list：{}",stringRedisTemplate.opsForList().leftPushAll("list1","1","2","3"));
        log.info("取list：{}",stringRedisTemplate.opsForList().range("list1",1,10));

    }



    @Test
    public void redisTemplateTest(){
        log.info("存字符串：");
        User user = userDao.selectById(1);
        redisTemplate.opsForValue().set("user-01",user);
        log.info("取字符串：{}",redisTemplate.opsForValue().get("user-01"));


        log.info("存list：");
        List<User> userList = userDao.selectList(new QueryWrapper<>());
        redisTemplate.opsForList().leftPush("user-list",userList);
        log.info("取list：{}",redisTemplate.opsForList().range("user-list",0,10));

    }



    @Test
    public void myRedisTemplateTest(){
        log.info("存字符串：");
        User user = userDao.selectById(2);
        myRedisTemplate.opsForValue().set("user-02",user);
        log.info("取字符串：{}",myRedisTemplate.opsForValue().get("user-02"));
    }

}
