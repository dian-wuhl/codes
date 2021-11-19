// Copyright 2016-2101 mi.
package com.mi.basic.service.impl;

import com.mi.basic.domain.SysUser;
import com.mi.basic.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author Dian
 * @Date 2021/11/8 15:22
 * @ModifyDate 2021/11/8 15:22
 * @Version 1.0
 */
@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private ISysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String userCode) throws UsernameNotFoundException {
        SysUser user = this.userService.getByUserCode(userCode);
        if (user == null) throw new UsernameNotFoundException("用户名不存在");
        //用户权限
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        /*if (StringUtils.isNotBlank(user.getRoles())) {
            String[] roles = user.getRoles().split(",");
            for (String role : roles) {
                if (StringUtils.isNotBlank(role)) {
                    authorities.add(new SimpleGrantedAuthority(role.trim()));
                }
            }
        }*/
        return new org.springframework.security.core.userdetails.User(user.getUserCode(), user.getUserPwd(), authorities);
    }
}
