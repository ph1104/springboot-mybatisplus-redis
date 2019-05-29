package com.springboot.web.demo.controller;

import com.springboot.web.demo.entity.Company;
import com.springboot.web.demo.entity.User;
import com.springboot.web.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 *
 * @CacheConfig :基于类的缓存注解
 *
 */
@CacheConfig(cacheNames = "user")
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private RedisCacheManager myCacheManager;



    /**
     *
     *  @Cacheable: 方法运行之前，先去查询缓存，有就去缓存取
     *              没有就调用方法，并将结果存入缓存
     * @param id
     * @return
     */
    @Cacheable(value = {"user"},key = "#a0")
    @GetMapping("{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getById(id);
    }


    /**
     * @CachePut : 既调用方法，又更新缓存
     *             先调用方法，将方法的结果缓存
     *
     *
     * @param user
     * @return
     */
    @CachePut(value = {"user"},key = "#user.id")
    @PutMapping
    public User updateUser(@RequestBody User user) {
        userService.updateById(user);
        return user;
    }


    /**
     *
     * @CacheEvict: 清除缓存
     *
     *
     * @param id
     * @return
     */
    @CacheEvict(value = "user",key = "#id")
    @DeleteMapping("{id}")
    public Boolean deleteUser(@PathVariable Long id){
        return userService.removeById(id);
    }



    @Cacheable(key = "1001")   //使用基于类的cacheNames
    @GetMapping("/list")
    public List<User> getUserList() {
        return userService.list();
    }





}