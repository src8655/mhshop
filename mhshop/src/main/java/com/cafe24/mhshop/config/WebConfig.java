package com.cafe24.mhshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.cafe24.config.web.MVCConfig;
import com.cafe24.config.web.SecurityConfig;
import com.cafe24.config.web.SwaggerConfig;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.mhshop.controller"})
@Import({SecurityConfig.class, SwaggerConfig.class, MVCConfig.class})
public class WebConfig{
	
	
}
