package com.gaoyg.monkeyzicloud.commom.core.config;

import com.gaoyg.monkeyzicloud.config.properties.MonkeyziCloudProperties;
import com.gaoyg.monkeyzicloud.config.properties.SwaggerProperties;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfiguration   {

	@Autowired
	private MonkeyziCloudProperties  monkeyziCloudProperties;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("monkeyzicloud-api")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		SwaggerProperties swagger=monkeyziCloudProperties.getSwaggerProperties();
		return new ApiInfoBuilder()
				.title(swagger.getTITLE())
				.description(swagger.getDESCRIPTION())
				.version(swagger.getVERSION())
				.license(swagger.getLICENSE())
				.licenseUrl(swagger.getLICENSEURL())
				.contact(new Contact(swagger.getCONTACTNAME(),swagger.getCONTACTURL(),swagger.getCONTACTEMAIL()))
				.build();
	}



}