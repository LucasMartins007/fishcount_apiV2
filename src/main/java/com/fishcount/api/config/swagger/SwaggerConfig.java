
package com.fishcount.api.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 *
 * @author lucas
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Value("${app.name}")
    private String appName;

    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.description}")
    private String description;

    @Value("${swagger.version}")
    private String version;

    @Value("${swagger.contact-name}")
    private String contactName;

    @Value("${swagger.contact-url}")
    private String contacUrl;

    @Value("${swagger.contact-email}")
    private String contactEmail;

    @Value("${api.base-path}/**")
    private String basePath;

    private static final String BASE_PACKAGE = "com.fishcount.api.controller";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .groupName(appName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.ant(basePath))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .contact(new Contact(contactName, contacUrl, contactEmail))
                .build();
    }

}
