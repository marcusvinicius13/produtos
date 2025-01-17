package com.produtos.apirest.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.produtos.apirest"))
				.paths(regex ("/api.*"))
				.build()
				.apiInfo(metaInfo());
	}
	
	@SuppressWarnings("rawtypes")
	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"Produtos Api REST", 
				"API REST de Cadastro de produtos.", 
				"1.0", 
				"Terms of Service", 
				new Contact(
						"Marcus Vinicius", 
						"https://github.com/marcusvinicius13", 
						"marcusvinicius1_3@hotmail.com"), 
				"Apache License Version 2.0", 
				"https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
		);
		return apiInfo;
	}
	
}
