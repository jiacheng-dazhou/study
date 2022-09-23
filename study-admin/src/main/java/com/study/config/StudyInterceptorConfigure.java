package com.study.config;

import com.study.interceptor.StudyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class StudyInterceptorConfigure implements WebMvcConfigurer {

    public static final String[] EXCLUDE_PATH = {};

    @Resource
    private StudyInterceptor studyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(studyInterceptor).addPathPatterns("/**").excludePathPatterns(EXCLUDE_PATH);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
