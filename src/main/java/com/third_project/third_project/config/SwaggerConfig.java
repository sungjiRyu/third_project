package com.third_project.third_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI thirdOpenAPI(){
        Info info =
                new Info().version("0.0.1").title("3차 프로젝트 API").description("3차 프로젝트 Restful API 명세서");
        //          // SecuritySecheme명
        // String jwtSchemeName = "jwtAuth";
        // // API 요청헤더에 인증정보 포함
        // SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        // // SecuritySchemes 등록
        // Components components = new Components()
        //         .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
        //                         .name(jwtSchemeName)
        //                         .type(SecurityScheme.Type.HTTP) // HTTP 방식
        //                         .scheme("bearer")
        //                         .bearerFormat("JWT")); // 토큰 형식을 지정하는 임의의 문자(Optional)

        // return new OpenAPI()
        //         .info(info)
        //         .addSecurityItem(securityRequirement)
        //         .components(components);
        return new OpenAPI().info(info);
    }

}

    

