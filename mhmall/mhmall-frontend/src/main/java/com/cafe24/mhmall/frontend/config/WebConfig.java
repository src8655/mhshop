package com.cafe24.mhmall.frontend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.cafe24.mhmall.frontend.config.web.MVCConfig;
import com.cafe24.mhmall.frontend.config.web.SecurityConfig;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.mhmall.frontend.controller", "com.cafe24.mhmall.frontend.exception"})
@Import({ SecurityConfig.class, MVCConfig.class })
public class WebConfig {
}
