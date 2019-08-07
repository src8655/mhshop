package com.cafe24.mhmall.frontend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.cafe24.mhmall.frontend.config.app.AppSecurityConfig;
import com.cafe24.mhmall.frontend.config.app.OAuth2ClientConfig;



@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.mhmall.frontend.security", "com.cafe24.mhmall.frontend.service.impl", "com.cafe24.mhmall.frontend.repository", "com.cafe24.mhmall.frontend.aspect"})
@Import({ AppSecurityConfig.class, OAuth2ClientConfig.class })
public class AppConfig {
}