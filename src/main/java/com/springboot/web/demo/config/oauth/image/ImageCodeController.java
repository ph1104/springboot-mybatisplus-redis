package com.springboot.web.demo.config.oauth.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ImageCodeController {

    private static final String SESSION_KEY = "SESSION_KEY_IAMGE_CODE";



    private ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 图形验证码生成步骤：
     *
     *   1、根据随机数生成图片
     *
     *   2、将随机数存入到session中
     *
     *   3、将生成的图片写入到响应
     */

    @GetMapping("/createImageCode")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = imageCodeGenerator.generateCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }
}
