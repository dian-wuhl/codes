// Copyright 2016-2101 mi.
package com.mi.common.annotation;


import com.mi.common.config.MybatisPlusConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description MybatisPlusConfig 注解
 * @Author Dian
 * @Date 2021/9/6 17:16
 * @ModifyDate 2021/9/6 17:16
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({MybatisPlusConfig.class})
public @interface EnabledMPConfig {
}
