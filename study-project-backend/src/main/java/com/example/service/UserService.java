package com.example.service;

import com.example.entity.user.AccountInfo;

public interface UserService {

    boolean saveUserInfo(AccountInfo info);

    AccountInfo getUserInfo(int uid);
}
