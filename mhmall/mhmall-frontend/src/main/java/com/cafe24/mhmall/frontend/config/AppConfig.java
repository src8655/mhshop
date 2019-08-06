package com.cafe24.mhmall.frontend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;



@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.mhmall.frontend.security", "com.cafe24.mhmall.frontend.service.impl", "com.cafe24.mhmall.frontend.repository", "com.cafe24.mhmall.frontend.aspect"})
public class AppConfig {
}