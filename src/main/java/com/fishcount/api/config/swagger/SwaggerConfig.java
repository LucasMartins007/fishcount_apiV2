
package com.fishcount.api.config.swagger;

import com.fishcount.api.controller.UsuarioController;
import com.fishcount.common.utils.ListUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

/**
 *
 * @author lucas
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    
    @Bean
    public Docket portal() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("FishCount api")
                .select()
                .apis(RequestHandlerSelectors.basePackage(UsuarioController.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(ListUtil.toList(securityContext()))
                .securitySchemes(ListUtil.toList(apiKey()))
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        return new ApiInfoBuilder()
                .title("Fish-count API")
                .description("Created by Lucas")
                .version("1.0")
                .termsOfServiceUrl("http://fishcount.com")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return ListUtil.toList(new SecurityReference("JWT", authorizationScopes));
    }

}
