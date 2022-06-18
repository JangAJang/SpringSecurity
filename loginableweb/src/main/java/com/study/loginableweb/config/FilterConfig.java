package com.study.loginableweb.config;


import com.study.loginableweb.filter.MyFilter1;
import com.study.loginableweb.filter.MyFilter2;
import com.study.loginableweb.filter.MyFilter3;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<MyFilter1> filter1(){
        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
        bean.addUrlPatterns("/*"); //모든 주소에 대해서 filter가 적용된다.
        bean.setOrder(0); // 우선순위는 값이 작을 수록 앞이다.
        return bean;
    }
    @Bean
    public FilterRegistrationBean<MyFilter2> filter2(){
        FilterRegistrationBean<MyFilter2> bean = new FilterRegistrationBean<>(new MyFilter2());
        bean.addUrlPatterns("/*"); //모든 주소에 대해서 filter가 적용된다.
        bean.setOrder(2); // 우선순위는 값이 작을 수록 앞이다.
        return bean;
    }
    @Bean
    public FilterRegistrationBean<MyFilter3> filter3(){
        FilterRegistrationBean<MyFilter3> bean = new FilterRegistrationBean<>(new MyFilter3());
        bean.addUrlPatterns("/*"); //모든 주소에 대해서 filter가 적용된다.
        bean.setOrder(1); // 우선순위는 값이 작을 수록 앞이다.
        return bean;
    }
}
