package com.mattyeh.memberSystem;

import com.mattyeh.memberSystem.interceptor.AuthenticationInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MemberSystemApplication implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);
		registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/service/**");

	}

	public static void main(String[] args) {
		SpringApplication.run(MemberSystemApplication.class, args);
	}

}
