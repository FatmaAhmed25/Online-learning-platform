package com.example.onlinelearningplatform;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;

@SpringBootApplication
public class OnlineLearningPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineLearningPlatformApplication.class, args);
	}
//	@Bean
//	public OpenAPI customOpenAPI(@Value("${springdoc.version:2.5.0}") String appVersion) {
//		return new OpenAPI()
//				.components(new Components())
//				.info(new Info().title("Test Center Management API").version(appVersion)
//						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
//	}

}
