package com.blog.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;

import io.swagger.v3.oas.models.OpenAPI;


import io.swagger.v3.oas.models.security.SecurityRequirement;



@Configuration
@OpenAPIDefinition(
		info=@Info(
				title="Bogging Apis",
				description="blogging apis for create a blog application",
				version="1.0",
				contact=@Contact(
						name="Shivam Maurya",
						email="shivammaurya@gmail.com",
						url="https://springshop.wiki.github.org/docs"
						),
				license=@License
				(
						name="Apache 2.0",
						url="http://springdoc.org"
						)
				
				),
		externalDocs = @ExternalDocumentation(
				description="SpringShop Wiki Documentation",
				url="https://springshop.wiki.github.org/docs"
				
				)
		
		
		)

@SecurityScheme(
		name="scheme1",
		type=SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer"
		)
public class SwaggerConfig {
	
	
//	//public static final String Authorization_Header="Authorization";
//	
//	  @Bean
//	  public OpenAPI springShopOpenAPI() {
//		  
//		  String schemeName="bearerScheme";
//	      return new OpenAPI()
//	    		  .addSecurityItem(new SecurityRequirement().
//	    				  addList(schemeName)
//	    				  
//	    				  )
//	    		  .components(new Components().addSecuritySchemes(schemeName, new SecurityScheme()
//	    				  .name(schemeName)
//	    				  .type(SecurityScheme.Type.HTTP)
//	    				  .bearerFormat("JWT")
//	    				  .scheme("bearer")
//	    				  ))
//	              .info(new Info().title("SpringShop API")
//	              .description("Spring shop sample application")
//	              .version("v0.0.1")
//	              .license(new License().name("Apache 2.0").url("http://springdoc.org")))
//	              .externalDocs(new ExternalDocumentation()
//	              .description("SpringShop Wiki Documentation")
//	              .url("https://springshop.wiki.github.org/docs"));
//	  }



}
