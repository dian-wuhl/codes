// Copyright 2016-2101 mi.
package com.mi.basic.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mi.common.api.CommonResult;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author Dian
 * @Date 2021/11/8 16:37
 * @ModifyDate 2021/11/8 16:37
 * @Version 1.0
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //设置返回类型
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        //将token信息写入
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(CommonResult.success("退出成功")));
    }
}
