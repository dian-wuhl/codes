// Copyright 2016-2101 mi.
package com.mi;

import com.mi.common.annotation.EnabledMPConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description 项目启动类
 * @Author Dian
 * @Date 2021/8/30 11:47
 * @ModifyDate 2021/8/30 11:47
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan({"com.mi.*.mapper"})
public class StartApp {
    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);
    }
}
