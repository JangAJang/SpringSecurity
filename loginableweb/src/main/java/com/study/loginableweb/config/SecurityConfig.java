package com.study.loginableweb.config;

import com.study.loginableweb.filter.MyFilter1;
import com.study.loginableweb.filter.MyFilterSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(new MyFilterSecurity(), BasicAuthenticationFilter.class);
        //이렇게 함으로써 기본적인 security의 필터 이전에 다른 필터를 적용시킬 수 있다.
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/user/**")
                .access("hasRole(ROLE_USER) or hasRole(ROLE_MANAGER) or hasRole(ROLE_ADMIN)")
                .antMatchers("/api/v1/manager/**")
                .access("hasRole(ROLE_MANAGER) or hasRole(ROLE_ADMIN)")
                .antMatchers("/api/v1/admin/**")
                .access("hasRole(ROLE_ADMIN)")
                .anyRequest().permitAll();
    }
}
