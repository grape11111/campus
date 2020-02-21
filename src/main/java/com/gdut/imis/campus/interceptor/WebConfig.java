package com.gdut.imis.campus.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> list=new ArrayList<>();
        list.add("/campus");
        list.add("/register");
        list.add("/logout");
        list.add("/js/**");
        list.add("/css/**");
        list.add("/fonts/**");
        list.add("/img/**");
        list.add("/static/**");
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**").excludePathPatterns(list);
    }
}