package com.example.service.impl;

import com.example.entity.auth.Account;
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
    public boolean saveUserInfo(AccountInfo info) {
        Account account = userMapper.findAccountByNameOrEmail(info.getUsername());
        if (account == null){
            userMapper.updateUserName(info.getUsername(), info.getUid());
        }else if (account.getId() != info.getUid()){
            return false;
        }
        userMapper.saveUserInfo(info);
        return true;
    }
}
