// Copyright 2016-2101 mi.
package com.mi.basic.filter;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mi.common.api.CommonResult;
import com.mi.common.api.ResultCode;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @Description TODO
 * @Author Dian
 * @Date 2021/11/8 16:39
 * @ModifyDate 2021/11/8 16:39
 * @Version 1.0
 */
@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${jwt.header}")
    private String header;
    @Value("${jwt.secret}")
    private String secret;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //获取JWT
        String token = request.getHeader(header);
        if (StringUtils.isNotBlank(token)) {
            // 解析token.
            Claims claims = null;
            try {
                claims = Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token)
                        .getBody();
                Date created = claims.get("created", Date.class);
                Date refreshDate = new Date();
                //如果token在30分钟之内刚刷新过，则不
                if(refreshDate.after(created) && refreshDate.before(DateUtil.offsetSecond(created, 30*60))){

                }
            } catch (ExpiredJwtException e) {
                logger.error("token 已过期");
                response.getWriter().write(objectMapper.writeValueAsString(CommonResult.failed(ResultCode.UNAUTHORIZED)));
            } catch (Exception e) {
                logger.error("token 解析异常，错误原因：", e);
                response.getWriter().write(objectMapper.writeValueAsString(CommonResult.failed(ResultCode.UNKNOWN)));
            }

            // 获取保存在token中的登陆认证成功的authentication，
            // 利用UsernamePasswordAuthenticationToken生成新的authentication
            // 放入到SecurityContextHolder，表示认证经过
            Object tokenInfo = claims.get("authentication");
            //经过com.alibaba.fastjson将其在转换
            Authentication authentication = JSONObject.parseObject(new JSONObject().toJSONString(tokenInfo), UsernamePasswordAuthenticationToken.class);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
