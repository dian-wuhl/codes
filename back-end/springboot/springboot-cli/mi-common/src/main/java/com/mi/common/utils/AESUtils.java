// Copyright 2016-2101 mi.
package com.mi.common.utils;

import com.baomidou.mybatisplus.core.toolkit.AES;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description AES 工具类
 * @Author Dian
 * @Date 2021/8/30 16:46
 * @ModifyDate 2021/8/30 16:46
 * @Version 1.0
 */
public class AESUtils {
    public static void main(String[] args) {
        // 生成 16 位随机 AES 密钥
        // 6bbde55645ccb2cd
        String randomKey = AES.generateRandomKey();
        System.out.println(randomKey);
        // 随机密钥加密
        String url = AES.encrypt("jdbc:mysql://localhost:3306/springboot-cli?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai", randomKey);
        String user = AES.encrypt("root", randomKey);
        String pwd = AES.encrypt("root", randomKey);
        //W9Hwy2nTejWg8HhGCHN4ZQHjRBz+RUo7PXjyqXbM/+Al26jZ2HTxtFlkaTUfhdyl/oPrLgQHk7PdlhU6PkQowb08S4QDpbSQGWZT3K+xrh+SuhAofdmv7xj/T0Cht8IXMAx487z+jbgjyXA8chDP0w==
        System.out.println(url);
        //AoF5H1zNRSQdlEoMXt+cfg==
        System.out.println(user);
        System.out.println(pwd);
    }
}
