package com.example.demo.Shiro;


import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SimpleCORSFilter extends AdviceFilter {
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String origin = httpRequest.getHeader("Origin");
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader( "Access-Control-Allow-Origin", origin );
        res.setHeader("Access-Control-Allow-Credentials", "true");
        WebUtils.toHttp(response).setContentType("application/json; charset=utf-8");
        return true;
    }
}

