// Copyright 2016-2101 mi.
package com.mi.basic.config;

import com.mi.basic.filter.AuthTokenFilter;
import com.mi.basic.handler.MyLoginFailureHandler;
import com.mi.basic.handler.MyLoginSuccessHandler;
import com.mi.basic.handler.MyLogoutSuccessHandler;
import com.mi.basic.service.impl.UserDetailService;
import com.mi.common.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Description security 配置
 * @Author Dian
 * @Date 2021/11/8 15:17
 * @ModifyDate 2021/11/8 15:17
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailService userService;
    @Autowired
    private AuthTokenFilter authTokenFilter;
    @Autowired
    private MyLoginSuccessHandler myLoginSuccessHandler;
    @Autowired
    private MyLoginFailureHandler myLoginFailureHandler;
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new PasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /** 将JWT拦截器添加到UsernamePasswordAuthenticationFilter以前*/
        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.formLogin()
                .usernameParameter("userCode")
                .passwordParameter("userPwd")
                .loginProcessingUrl("/login")
                .successHandler(myLoginSuccessHandler)
                .failureHandler(myLoginFailureHandler);
        http.authorizeRequests()
                .antMatchers("/login","/randomCode" ).permitAll()
                .anyRequest().authenticated();
        //访问 /logout 表示用户注销
        http.logout().logoutSuccessHandler(myLogoutSuccessHandler);
        // 关闭csrf
        http.csrf().disable();
        http.cors();
    }
}