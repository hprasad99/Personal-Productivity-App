package com.cap.ppa.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringSwaggerFox extends WebMvcConfigurationSupport{
	@Bean
    public Docket Todoapi() {
        // @formatter:off
        //Register the controllers to swagger
        //Also it is configuring the Swagger Docket
        return new Docket(DocumentationType.SWAGGER_2).select()
                 .apis(RequestHandlerSelectors.basePackage("com.cap.ppa.controller"))
                .paths(PathSelectors.any())

                .build()
                .apiInfo(apiDetails());
        // @formatter:on
    }
	
	private ApiInfo apiDetails()
	{
		List<VendorExtension> vendor= new ArrayList<>();
		return new ApiInfo(
				"Personal Productivity Application",
				"Calender-based App",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Team PPA", "https://personalproductivityapp.com", "contact@ppa.com"),
				"https://personalproductivityapp.com",
				"Api License",vendor
				);
	}
	
	
    public void addResourceHandlers(ResourceHandlerRegistry registry) 
    {
        //enabling swagger-ui part for visual documentation
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
