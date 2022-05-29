package com.cos.jwt.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

//시큐리티가 필터를 가지고 있는데, 그 필터중에 BasicAuthentificationFilter가 존재.
//그러면 권한이나 인증이 필요한 특정 주소를 요청했을 떄, 위 필터를 무조건 타게 된다.
//만약 권한이나 인증이 필요한 주소가 아니라면, 이 필터를 안 타도 된다.
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        System.out.println("인증이나 권한이 필요한 주소 요청이 됨");
    }

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }


}
