package com.statistics.vector.config;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.statistics.vector.constants.Constants;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.statistics.vector.controller")) 
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(Constants.VECTORSTATSSERVICE, Constants.DESCRIPTION , "", "", "",
				"", Constants.URL);
		
	}
}
