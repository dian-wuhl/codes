// Copyright 2016-2101 mi.
package com.mi.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description MybatisPlus 自动填充处理器
 * @Author Dian
 * @Date 2021/8/30 16:35
 * @ModifyDate 2021/8/30 16:35
 * @Version 1.0
 */
@Slf4j
@Component
public class MybatisPlusHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createdBy", Long.class, 11111111L);
        this.strictInsertFill(metaObject, "createdOn", Date.class, new Date());
        this.strictUpdateFill(metaObject, "updatedBy", Long.class, 11111111L);
        this.strictUpdateFill(metaObject, "updatedOn", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updatedBy", Long.class, 22222222L);
        this.strictUpdateFill(metaObject, "updatedOn", Date.class, new Date());
    }
}
