package com.gdudek.movieRental.security;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CustomUsernameAuthenticationFilter extends GenericFilterBean {




    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if(servletRequest instanceof HttpServletRequest) {
            HttpServletRequest r = (HttpServletRequest)servletRequest;
            String loginType = r.getParameter("userType");
            r.getSession().setAttribute("userType", loginType);
            System.out.println("Filter" + loginType);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
