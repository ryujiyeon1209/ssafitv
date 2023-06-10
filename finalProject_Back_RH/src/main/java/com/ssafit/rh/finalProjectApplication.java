package com.ssafit.rh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan(basePackages="com.ssafit.rh.model.dao")
public class finalProjectApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(finalProjectApplication.class, args);
	}

}
