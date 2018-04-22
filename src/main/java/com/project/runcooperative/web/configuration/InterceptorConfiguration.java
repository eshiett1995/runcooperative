package com.project.runcooperative.web.configuration;

import com.project.runcooperative.web.interceptors.GenericInterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Oto-obong on 01/02/2018.
 */

@Configuration
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    GenericInterceptors genericInterceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(genericInterceptors).excludePathPatterns("/")
                .excludePathPatterns("/login/authenticate")
                .excludePathPatterns("/view/customer")
                .excludePathPatterns("/login/authenticatecustomer")
                .excludePathPatterns("/populate")
                .excludePathPatterns("/customerlogin")
                .excludePathPatterns("/loan/regular-add")
                .excludePathPatterns("/home")
                .excludePathPatterns("/customer/change-password")
                .excludePathPatterns("/LoginCustomerController")
                .excludePathPatterns("/customer-home")
                .excludePathPatterns("/approve/loans")
                .excludePathPatterns("/view/loans/{id}")
                .excludePathPatterns("/loan/emergency/add")
                .excludePathPatterns("/{email}/loans")
                .excludePathPatterns("/error");

    }


}
