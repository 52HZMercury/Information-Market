package com.lostandfound.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @program: LostandFound
 * @description:
 * @author: Mr.Like
 * @create: 2021-11-22 20:49
 **/
@Configuration
@EnableOpenApi
@EnableWebMvc
public class SwaggerConfig {


    @Bean
    public Docket docket(){


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)//默认为true 关闭后，则不能使用swagger
                .select()
                //指定接口的位置
                .apis(RequestHandlerSelectors.basePackage("com.lostandfound.controller"))
                //过滤什么路径
                //.paths()
                .build();
        //.globalOperationParameters(pars);

    }

    private ApiInfo apiInfo() {
        //作者信息
        Contact contact = new Contact("陈宁","https://mercurys-52hz.gitee.io/","chening_william@163.com");

        return new ApiInfo(
                "陈宁的接口文档",
                "前端根据接口进行测试",
                "1.0",
                "https://mercurys-52hz.gitee.io/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}