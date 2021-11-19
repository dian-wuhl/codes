package com.mi.basic.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mi.basic.dto.UserLoginParam;
import com.mi.basic.dto.UserParam;
import com.mi.basic.dto.UserPwdUpdateParam;
import com.mi.basic.dto.UserRegisterParam;
import com.mi.basic.mapper.SysUserMapper;
import com.mi.basic.domain.SysUser;
import com.mi.basic.service.ISysUserService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Description 用户管理
 * @Author Dian
 * @Date 2021/9/2 11:38
 * @ModifyDate 2021/9/2 11:38
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public Page<SysUser> list(String searchVal, Integer pageSize, Integer pageNum) {
        Page<SysUser> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SysUser> wrapperLambda = wrapper.lambda();
        if(StringUtils.isNotBlank(searchVal)){
            wrapperLambda.eq(SysUser::getUserCode, searchVal);
            wrapperLambda.or().eq(SysUser::getName, searchVal);
        }
        return page(page, wrapper);
    }

    /**
     * 用户注册
     * @param param
     * @return
     */
    @Override
    public SysUser register(UserRegisterParam param) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(param, user);
        user.setEnabled(1);
        //查询是否存在相同用户名
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUser::getUserCode, user.getUserCode());
        List<SysUser> users = list(wrapper);
        if(users.size() > 0){
            return null;
        }
        // 将密码进行加密操作
        String encodePwd = BCrypt.hashpw(user.getUserPwd());
        user.setUserPwd(encodePwd);
        this.baseMapper.insert(user);
        return user;
    }

    @Override
    public boolean update(Long id, UserParam param) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(param, user);
        user.setId(id);
        user.setUserPwd(null);
        return this.updateById(user);
    }

    @Override
    public int updatePassword(UserPwdUpdateParam param) {
        if(StringUtils.isEmpty(param.getUserCode())
                || StringUtils.isEmpty(param.getOldPwd())
                || StringUtils.isEmpty(param.getNewPwd())){
            return -1;
        }
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUser::getUserCode, param.getUserCode());
        List<SysUser> users = this.list(wrapper);
        if(CollectionUtils.isEmpty(users)) return -2;
        SysUser user = users.get(0);
        if(!BCrypt.checkpw(param.getOldPwd(), user.getUserPwd())) return -3;
        user.setUserPwd(BCrypt.hashpw(param.getNewPwd()));
        this.updateById(user);
        return 1;
    }

    @Override
    public boolean delete(Long id) {
        return this.removeById(id);
    }

    @Override
    public SysUser getByUserCode(String userCode) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUser::getUserCode, userCode);
        List<SysUser> users = this.list(wrapper);
        if(!CollectionUtils.isEmpty(users)) return users.get(0);
        return null;
    }

    @Override
    public SysUser login(UserLoginParam param) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUser::getUserCode, param.getUserCode());
        SysUser user = this.baseMapper.selectOne(wrapper);
        if(user != null && BCrypt.checkpw(param.getUserPwd(), user.getUserPwd())) {
            return user;
        }
        return null;
    }
}
