package com.example.interceptor;

import com.example.entity.user.AccountUser;
import com.example.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 拦截器
 * @author Jinze
 */
@Component
public class AuthorizeInterceptor implements HandlerInterceptor {
    @Resource
    UserMapper mapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取当前的安全上下文
        SecurityContext context = SecurityContextHolder.getContext();
        // 获取认证信息
        Authentication authentication = context.getAuthentication();
        // 从认证信息中获取用户对象
        User user = (User) authentication.getPrincipal();
        // 获取用户的用户名
        String username = user.getUsername();
        // 根据用户名或邮箱查询账户用户信息
        AccountUser account = mapper.findAccountUserByNameOrEmail(username);
        // 将账户用户信息存储到请求的会话中
        request.getSession().setAttribute("account", account);
        return true;
    }
}
