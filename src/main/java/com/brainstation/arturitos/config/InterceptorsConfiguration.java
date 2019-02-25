package com.brainstation.arturitos.config;

import com.brainstation.arturitos.Web.interceptors.MyRequestHeaderInterceptor;

import com.brainstation.arturitos.utils.JwtUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new MyRequestHeaderInterceptor(new JwtUtil()))
                .excludePathPatterns("/user")
                .excludePathPatterns("/user/*")
                .excludePathPatterns("/user/uploadImage")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/product")
                .excludePathPatterns("/product/*")
                .excludePathPatterns("/product/category/*")
                .excludePathPatterns("/category");
    }

}
