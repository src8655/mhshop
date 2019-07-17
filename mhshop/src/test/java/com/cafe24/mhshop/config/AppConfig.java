package com.cafe24.mhshop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.cafe24.mhshop.config.app.TestDBConfig;
import com.cafe24.mhshop.config.app.TestMybatisConfig;
import com.cafe24.mhshop.config.web.SwaggerConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.mhshop.service.impl","com.cafe24.mhshop.repository.impl"})
@Import({TestDBConfig.class, TestMybatisConfig.class})
public class TestAppConfig {

}
