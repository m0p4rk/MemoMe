package com.m0p4rk.memome.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.m0p4rk.memome.common.interceptor.AuthenticationInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor())
                .addPathPatterns("/dashboard"); // 모든 경로에 대해 적용
//              .excludePathPatterns("/register.html","/login.html","/login.do"); // 특정 경로 제외 가능
    }
}
