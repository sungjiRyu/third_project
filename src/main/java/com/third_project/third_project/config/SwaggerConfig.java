package com.third_project.third_project.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI thirdOpenAPI(){
        Info info =
                new Info().version("0.0.1").title("3차 프로젝트 API").description("3차 프로젝트 Restful API 명세서");
        return new OpenAPI().info(info);
    }
}
