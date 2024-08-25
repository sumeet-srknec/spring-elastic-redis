package com.ssharma.demo;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"com.ssharma.demo", "com.ssharma.demo.*"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	/**
	 * Initialize Swagger documentation
	 * 
	 * @return
	 */
	private ApiInfo getApiInfo() {

		return new ApiInfo("spring-elastic-redis Service API's", "spring-elastic-redis Service REST calls", "1.0.0",
				"https://www.ssharma.com/",
				new springfox.documentation.service.Contact("spring-elastic-redis", "https://spring-elastic-redis/",
						"DL: spring-elastic-redis"),
				String.format("Copyright (c) 2024 ssharma Corporation. All rights reserved."), "https://www.ssharma.com/",
				new ArrayList<>());

	}

	@Bean
	public Docket getDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.ssharma.demo")).build().apiInfo(getApiInfo());
	}
}
