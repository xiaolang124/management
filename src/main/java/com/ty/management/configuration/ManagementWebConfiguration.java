package com.ty.management.configuration;

import com.ty.management.Interceptor.LoginRequiredInterceptor;
import com.ty.management.Interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class ManagementWebConfiguration extends WebMvcConfigurerAdapter{

    @Autowired
    LoginRequiredInterceptor loginRequiredInterceptor;

    @Autowired
    PassportInterceptor  passportInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(passportInterceptor);
        registry.addInterceptor(loginRequiredInterceptor).
                addPathPatterns("/user/update");

    }

}
