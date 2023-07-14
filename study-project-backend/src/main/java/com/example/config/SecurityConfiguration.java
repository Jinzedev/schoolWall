package com.example.config;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.RestBean;
import com.example.service.AuthorizeService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.io.IOException;

/***
 * 安全框架配置类
 * @author jinze
*/

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Resource
    AuthorizeService authorizeService;

    @Resource
    DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, PersistentTokenRepository repository) throws Exception {
        return http
                // 配置授权规则
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/auth/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                // 配置基于表单的登录
                .formLogin(conf -> {
                    conf.loginProcessingUrl("/api/auth/login"); // 指定登录表单提交的URL
                    conf.successHandler(this::onAuthenticationSuccess); // 成功认证的处理器
                    conf.failureHandler(this::onAuthenticationFailure); // 失败认证的处理器
                })
                // 配置注销
                .logout(conf -> {
                    conf.logoutUrl("/api/auth/logout"); // 指定注销请求的URL
                    // 成功注销的处理器
                    conf.logoutSuccessHandler(this::onAuthenticationSuccess);
                })
                // 配置记住我功能
                .rememberMe(conf -> {
                    conf.rememberMeParameter("remember"); // 指定记住我参数的名称
                    conf.tokenRepository(repository); // 指定持久化令牌的存储库
                    conf.tokenValiditySeconds(3600 * 24 * 7); // 指定令牌的有效期（秒）
                })
                // 禁用跨站请求伪造（CSRF）保护
                .csrf(AbstractHttpConfigurer::disable)
                .cors(conf -> {
                    // 配置跨域资源共享（CORS）
                    conf.configurationSource(this.corsConfigurationSource());
                })
                .exceptionHandling(conf -> {
                    // 配置身份验证入口点
                    conf.authenticationEntryPoint(this::onAuthenticationFailure);
                })
                .build();
    }


    @Bean
    public PersistentTokenRepository tokenRepository(){
        // 创建一个 JdbcTokenRepositoryImpl 对象用于持久化令牌
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 在启动时不创建令牌表
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

    /**
     * 配置跨域资源共享（CORS）的配置源
     */
    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.addAllowedOriginPattern("*");
        // 允许携带身份凭证（如Cookie）
        cors.setAllowCredentials(true);
        cors.addAllowedHeader("*");
        cors.addAllowedMethod("*");
        cors.addExposedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 对所有路径应用CORS配置
        source.registerCorsConfiguration("/**", cors);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity security) throws Exception {
        return security
                .getSharedObject(AuthenticationManagerBuilder.class)
                // 设置自定义的 UserDetailsService
                .userDetailsService(authorizeService)
                .and()
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 处理成功认证的回调方法
     */
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setCharacterEncoding("utf-8");
        if(request.getRequestURI().endsWith("/login")) {
            // 返回登录成功的响应
            response.getWriter().write(JSONObject.toJSONString(RestBean.success("登录成功")));
        } else if(request.getRequestURI().endsWith("/logout")) {
            // 返回退出登录成功的响应
            response.getWriter().write(JSONObject.toJSONString(RestBean.success("退出登录成功")));
        }
    }

    /**
     * 处理失败认证的回调方法
     */
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setCharacterEncoding("utf-8");
        // 返回认证失败的响应
        response.getWriter().write(JSONObject.toJSONString(RestBean.failure(401, exception.getMessage())));
    }

}
