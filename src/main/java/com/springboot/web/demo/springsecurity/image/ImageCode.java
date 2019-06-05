package com.springboot.web.demo.springsecurity.image;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;

/**
 * @author penghui
 *
 *
 * 图形验证码实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageCode {

    //验证码
    private String code;

    //图形验证码
    private BufferedImage image;



}
