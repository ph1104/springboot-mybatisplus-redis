package com.springboot.web.demo.springsecurity.image;

import com.springboot.web.demo.constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * 图形验证码请求controller
 * @author penghui
 * @date 2019/6/3 0003   16:20
 *
 */
@Slf4j
@RestController
public class ImageCodeController {



    private ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 图形验证码生成步骤：
     *
     *   1、根据随机数生成图片
     *
     *   2、将随机数存入到缓存中
     *
     *   3、将生成的图片写入到响应
     */

    @GetMapping("/createImageCode")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = imageCodeGenerator.generateCode();
        log.info("生成图形验证码：{}",imageCode.getCode());
        stringRedisTemplate.opsForValue().set(SecurityConstants.IMAGE_REDIS_KEY + imageCode.getCode(),imageCode.getCode(),1, TimeUnit.MINUTES);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }
}
