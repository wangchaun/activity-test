package com.kyubi.activitytest.config;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableKnife4j
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket customImplementation() {
        //添加head参数start
        ParameterBuilder tokenParam = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        tokenParam.name("HEADER")
                .description("Authorization")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .defaultValue("eyJjcmVhdGVUaW1lIjoxNTg4MDQwNDMzNTg3LCJwb3JjaFRva2VuIjoiNDU0ZGM5ZjNlODU1NDBhZmJhNGQ4M2MwOTg2NzRhZDUtNGE0OTM3NTJiZTVkNGVlNDg1ZGYxMTVhZjczNTAyZDYiLCJ1c2VySWQiOiI1YjBkMzdmMzk2MzRiMTc1NWU2OTZjYzgifQ==--F537E71D53AD776444D785312E214C4D")
                .build();
        parameters.add(tokenParam.build());
        //添加head参数end

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .globalOperationParameters(parameters)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kyubi.activitytest"))
                .paths(PathSelectors.any())
                .build()
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("test-activiti服务")
                .description("工作流服务")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

}