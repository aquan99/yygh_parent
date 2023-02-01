package com.wuwq.yygh.common.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.common.config
 * @ClassName:Swagger2Config
 * @Author:Wuwq
 * @Description:Swagger2配置类
 * @Date: 2022-03-21 13:50
 * @Version: 1.0
 */

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket yyghApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(yyghApiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo yyghApiInfo(){
        return new ApiInfoBuilder()
                .title("yygh接口文档")
                .contact(new Contact("wuwq","www.baidu.com","1786010034@qq.com"))
                .description("yygh接口文档")
                .version("1.0")
                .build();
    }

}
