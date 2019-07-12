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

import com.cafe24.mhshop.config.web.MVCConfig;
import com.cafe24.mhshop.config.web.SwaggerConfig;
import com.cafe24.mhshop.config.web.TestMVCConfig;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.mhshop.controller"})
@Import({SwaggerConfig.class, TestMVCConfig.class})
public class TestWebConfig{
	
	
}
