// Copyright 2016-2101 mi.
package com.mi.basic.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description TODO
 * @Author Dian
 * @Date 2021/11/8 16:38
 * @ModifyDate 2021/11/8 16:38
 * @Version 1.0
 */
@Component("myLoginFailureHandler")
public class MyLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    /** * ObjectMapper这个类是java中jackson提供的，主要是用来把对象转换成为一个json字符串返回到前端, */
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        //json形式返回
        //服务器内部异常
        response.setStatus(500);
        //设置返回类型
        response.setContentType("application/json;charset=UTF-8");
        //将错误信息写入
        response.getWriter().write(objectMapper.writeValueAsString(exception.getMessage()));
    }
}
