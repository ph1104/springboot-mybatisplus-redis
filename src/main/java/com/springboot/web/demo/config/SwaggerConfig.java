package com.springboot.web.demo.config;


import com.springboot.web.demo.constant.CommonConstants;
import com.springboot.web.demo.constant.SecurityConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {

        //Swagger2 JWT 整合增加token输入
        ParameterBuilder tokenBuilder = new ParameterBuilder();
        List<Parameter> parameterList = new ArrayList<>();
        tokenBuilder.name("Authorization")
                .defaultValue("Bearer ")
                .description("令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build();
        parameterList.add(tokenBuilder.build());


        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //api文档所需要扫描的包路径
                .apis(RequestHandlerSelectors.basePackage("com.springboot.web.demo.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(securityScheme()))
                .securityContexts(Collections.singletonList(securityContext()))
                .globalOperationParameters(parameterList);
    }


    //文档描述
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("权限管理系统")
                .version("1.0")
                .build();
    }

    /**
     * 这个类决定了使用哪种认证方式，这里使用密码模式
     *
     */
    private SecurityScheme securityScheme() {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(CommonConstants.SERVER_URL + SecurityConstants.USERNAME_PASS_TOKEN_URL);
        return new OAuthBuilder()
                .name("spring_oauth")
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

    /**
     * 这里设置 swagger2 认证的安全上下文
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("spring_oauth", scopes())))
                .forPaths(PathSelectors.any())
                .build();
    }

    /**
     * 这里是写允许认证的scope
     */
    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{
                new AuthorizationScope("all", "All scope is trusted!")
        };
    }
}
