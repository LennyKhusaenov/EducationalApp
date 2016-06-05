package com.educationportal.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfigController extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("index");
//        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/upload").setViewName("uploadFile");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/files").setViewName("files");
    }

}
