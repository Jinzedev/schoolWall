package com.example.controller;

import com.example.entity.RestBean;
import com.example.entity.user.AccountInfo;
import com.example.entity.user.AccountUser;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户
 * @author Jinze
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/me")
    public RestBean<AccountUser> me(@SessionAttribute("account") AccountUser user){
        return RestBean.success(user);
    }
    @PostMapping("/save-info")
    public RestBean<String> saveUserInfo(@RequestBody @Validated AccountInfo info,
                                         @SessionAttribute("account") AccountUser user){
        info.setUid(user.getId());
        if (userService.saveUserInfo(info)) {
            user.setUsername(info.getUsername());
            return RestBean.success();
        }else {
            return RestBean.failure(400,"用户名已被其他用户使用,无法修改");
        }

    }

    @GetMapping("/getUserInfo")
    public RestBean<AccountInfo> getUserInfo(@SessionAttribute("account") AccountUser user){
        return  RestBean.success(userService.getUserInfo(user.getId()));
    }
}
