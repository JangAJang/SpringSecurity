package com.study.loginableweb.filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilterSecurity implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("FilterSecurity");
        chain.doFilter(request, response);
    }
}