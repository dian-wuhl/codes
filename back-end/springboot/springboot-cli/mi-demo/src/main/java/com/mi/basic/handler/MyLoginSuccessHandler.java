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
public class MyLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        // 登陆成功后设置JWT
        String token = Jwts.builder()
                //设置token的信息
                // .setClaims(claimsMap)
                //将认证后的authentication写入token，验证时，直接验证它
                .claim("authentication", authentication)
                .claim("created", new Date())
                //过时时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                //加密方式
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        httpServletResponse.addHeader("token", token);
        //要作的工做就是将Authentication以json的形式返回给前端。 须要工具类ObjectMapper，Spring已自动注入。
        //设置返回类型
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        //this.redisUtil.set(token, userVo, 30 * 60 );
        //将token信息写入
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(CommonResult.success(result)));
    }
}
