package com.cafe24.mhmall.config.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cafe24.mhmall.security.AuthInterceptor;
import com.cafe24.mhmall.security.AuthUserHandlerMethodArgumentResolver;
import com.cafe24.mhmall.security.BasicInterceptor;

@Configuration
@EnableWebMvc
public class SecurityConfig extends WebMvcConfigurerAdapter {

	//
	// Argument Resolver
	//
	@Bean
	public AuthUserHandlerMethodArgumentResolver authUserHandlerMethodArgumentResolver() {
		return new AuthUserHandlerMethodArgumentResolver();
	}
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(authUserHandlerMethodArgumentResolver());
	}

	
	//
	// Interceptor
	//
	@Bean
	public AuthInterceptor authInterceptor() {
		return new AuthInterceptor();
	}
	@Bean
	public BasicInterceptor basicInterceptor() {
		return new BasicInterceptor();
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
		.addInterceptor(authInterceptor())
		.excludePathPatterns("/api/member/login")
		.excludePathPatterns("/api/member/logout")
		.excludePathPatterns("/member/login")
		.excludePathPatterns("/member/logout")
		.excludePathPatterns("/assets/**");

		registry
		.addInterceptor(basicInterceptor());
		
	}
}
