// Copyright 2016-2101 mi.
package com.mi.common.utils;

import cn.hutool.crypto.digest.BCrypt;

/**
 * @Description Security 密码处理
 * @Author Dian
 * @Date 2021/11/8 15:23
 * @ModifyDate 2021/11/8 15:23
 * @Version 1.0
 */
public class PasswordEncoder implements org.springframework.security.crypto.password.PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return BCrypt.hashpw((String) rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return BCrypt.checkpw((String) rawPassword, encodedPassword);
    }
}
