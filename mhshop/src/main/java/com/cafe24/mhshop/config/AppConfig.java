package com.cafe24.mhshop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.cafe24.config.app.DBConfig;
import com.cafe24.config.app.MybatisConfig;
import com.cafe24.config.web.SwaggerConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.mhshop.service.impl","com.cafe24.mhshop.repository.impl"})
@Import({DBConfig.class, MybatisConfig.class})
public class AppConfig {

}
