package com.n26.transaction.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * Configuration for the swagger integration
 * 
 * @author Sanjib Pramanick
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").apiInfo(apiInfo()).select()
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Transaction Service APIs").description("Transaction Service APIs Reference")
				.termsOfServiceUrl("")
				.contact(new Contact("Transaction Management Team", "", "pramanick.sanjib@gmail.com"))
				.license("Open Source").licenseUrl("pramanick.sanjib@gmail.com").version("1.0").build();
	}
}
