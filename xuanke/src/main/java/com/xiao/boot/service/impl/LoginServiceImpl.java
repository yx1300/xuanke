package com.xiao.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.boot.bean.User;
import com.xiao.boot.mapper.LoginMapper;
import com.xiao.boot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginMapper loginMapper;
    @Override
    public User login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        return loginMapper.selectList(queryWrapper).get(0);
    }
}
