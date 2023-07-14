package com.example.config;

import com.example.interceptor.AuthorizeInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置类
 * @author Jinze
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Resource
    AuthorizeInterceptor interceptor;

    /***
     * 添加拦截器
     * @author jinze
    */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/auth/**");
    }
}
