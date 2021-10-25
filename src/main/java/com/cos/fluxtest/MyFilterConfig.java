package com.cos.fluxtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFilterConfig {
	
	@Autowired
	private EventNotify eventNotify;

	@Bean
	public FilterRegistrationBean<MyFilter> addFilter(){
		System.out.println("필터 등록 됨!!!");
		FilterRegistrationBean<MyFilter> bean = new FilterRegistrationBean<>(new MyFilter(eventNotify));
		bean.addUrlPatterns("/sse");
		return bean;
	}
	
	@Bean
	public FilterRegistrationBean<MyFilter2> addFilter2(){
		System.out.println("필터 등록 됨!!!");
		FilterRegistrationBean<MyFilter2> bean = new FilterRegistrationBean<>(new MyFilter2(eventNotify));
		bean.addUrlPatterns("/add");
		return bean;
	}
}
