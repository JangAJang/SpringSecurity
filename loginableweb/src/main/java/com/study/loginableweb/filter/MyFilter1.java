package com.study.loginableweb.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter1 implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        //토큰을 만든다고 하면, 인증이 되게 하고, 그게 아니면 필터를 계속 타서 인증이 되게하거나 인증을 하지 못해 진입하지 못하게 한다.
        if(req.getMethod().equals("POST")){
            System.out.println("POST 요청됨");
            String headerAuth = req.getHeader("Authorization");
            System.out.println(headerAuth);
            if(headerAuth.equals("cos")){
                chain.doFilter(req, res);
            }
            else{
                PrintWriter out = res.getWriter();
                System.out.println("인증안됨");
            }
        }
        System.out.println("Filter1");
    }
}
