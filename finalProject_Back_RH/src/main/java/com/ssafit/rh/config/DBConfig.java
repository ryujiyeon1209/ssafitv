package com.ssafit.rh.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages="com.ssafit.rh.model.dao")
public class DBConfig {
	
}
