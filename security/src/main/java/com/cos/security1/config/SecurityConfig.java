package com.cos.security1.config;

import com.cos.security1.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//secure 어노테이션 활성화시키기 => @Secured("ROLE_?")하면 조절가능
//prePostEnabled => preAuthorized라는 어노테이션 활성화.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*
    * 코드를 받으면, 액세스 토큰으로 권한을 받고, 사용자 프로필 정보를 가져오고
    * 1)회원가입을 진행
    * 2)회원가입창이 추가적으로 나와서 추가 진행 실시*/

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/manager/**")
                .access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")    //로그인 주소 호출되면 security가 낚아챔.
                .defaultSuccessUrl("/")
                .and()
                .oauth2Login()
                .loginPage("/loginForm") // 여기까지만 하면 후처리가 안돼서 로그인 후 창 이동이 안됨.
                .userInfoEndpoint()
                .userService(principalOauth2UserService);
    }
}
