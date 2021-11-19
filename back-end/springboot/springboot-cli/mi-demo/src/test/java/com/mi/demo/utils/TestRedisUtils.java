// Copyright 2016-2101 mi.
package com.mi.demo.utils;

import com.mi.StartApp;
import com.mi.basic.domain.SysUser;
import com.mi.basic.service.ISysUserService;
import com.mi.common.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description TODO
 * @Author Dian
 * @Date 2021/10/28 15:30
 * @ModifyDate 2021/10/28 15:30
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StartApp.class)
public class TestRedisUtils {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ISysUserService userService;

    @Test
    public void testSave(){
        SysUser user = this.userService.getById(1);
        this.redisUtil.set("1", user, 100);
        SysUser redisUser = (SysUser) this.redisUtil.get("1");
        System.out.println(redisUser.toString());
    }

    @Test
    public void testGetExpire(){
        long expire = this.redisUtil.getExpire("1");
        System.out.println("过期时间为：" + expire + "秒");
    }
}
