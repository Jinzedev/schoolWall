package com.example.service.impl;

import com.example.entity.user.AccountInfo;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void saveUserInfo(AccountInfo info) {

        userMapper.saveUserInfo(info);
    }
}
