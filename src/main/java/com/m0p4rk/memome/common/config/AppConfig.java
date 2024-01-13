package com.m0p4rk.memome.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.m0p4rk.memome.common.interceptor.AuthenticationInterceptor;

@Configuration
public class AppConfig implements WebMvcConfigurer {
	// Bean Configurations
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Interceptor Configurations
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AuthenticationInterceptor())
									.addPathPatterns("/dashboard");
	}

}
