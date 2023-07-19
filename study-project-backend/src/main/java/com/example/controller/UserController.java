package com.example.controller;

import com.example.entity.RestBean;
import com.example.entity.user.AccountInfo;
import com.example.entity.user.AccountUser;
import com.example.service.UserService;
import jakarta.annotation.Resource;
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
    public RestBean<String> saveUserInfo(@RequestBody AccountInfo info,
                                         @SessionAttribute("account") AccountUser user){
        info.setUid(user.getId());
        userService.saveUserInfo(info);
        return RestBean.success();
    }

}
