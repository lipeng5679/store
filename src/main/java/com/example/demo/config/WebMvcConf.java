package com.example.demo.config;

import com.example.demo.Interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2017/7/7.
 */
@Configuration
public class WebMvcConf extends WebMvcConfigurerAdapter {
    @Autowired
    private MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration interceptor = registry.addInterceptor(myInterceptor);
        interceptor.addPathPatterns("/user/back");
        interceptor.addPathPatterns("/supermarket/*");
    }

}
