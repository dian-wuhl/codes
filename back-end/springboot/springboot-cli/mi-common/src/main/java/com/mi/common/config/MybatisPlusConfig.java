// Copyright 2016-2101 mi.
package com.mi.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.mi.common.handler.MybatisPlusHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description MybatisPlus 配置
 * @Author Dian
 * @Date 2021/8/30 14:46
 * @ModifyDate 2021/8/30 14:46
 * @Version 1.0
 */
@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {

    /**
     * MybatisPlus插件
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //分页组件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        //乐观锁
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

    /**
     * MybatisPlus自动填充
     * @return
     */
    @Bean
    public MybatisPlusHandler mybatisPlusHandler(){
        return new MybatisPlusHandler();
    }

}
