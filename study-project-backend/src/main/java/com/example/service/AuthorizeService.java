package com.example.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthorizeService extends UserDetailsService {
    /***
     * 发送邮箱验证码
     * @param email 邮箱
     * @param sessionId sessionId
     * @param hasAccount 用户是否存在
     * @return java.lang.String
     * @author jinze
    */
    String sendValidateEmail(String email, String sessionId, boolean hasAccount);

    /***
     * 验证并注册
     * @param username 用户名
     * @param password 密码
     * @param email 邮箱
     * @param code 验证码
     * @param sessionId sessionId
     * @return java.lang.String
     * @author jinze
    */
    String validateAndRegister(String username, String password, String email, String code, String sessionId);

    /***
     * 校验验证码
     * @param email 邮箱
     * @param code 验证码
     * @param sessionId sessionId
     * @return java.lang.String
     * @author jinze
    */
    String validateOnly(String email, String code, String sessionId);

    /***
     * 根据邮箱改密码
     * @param password 密码
     * @param email 邮箱
     * @return boolean
     * @author jinze
    */
    boolean resetPassword(String password, String email);
}
