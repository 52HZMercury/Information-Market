package com.lostandfound.config;

/**
 * @program: LostandFound
 * @description:
 * @author: Mr.Like
 * @create: 2021-12-10 20:55
 **/

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    //配置映射路径，使得前端可以访问文件的地址
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:/www/wwwroot/LostandFound/backgroundSystem/lostandfoundimg/");
    }
    //D:\文档\新鲜出炉的程序\SpringBoot\LostandFound\src\main\resources\LostandFoundimg
    // linux  /www/wwwroot/LostandFound/backgroundSystem/lostandfoundimg/



}