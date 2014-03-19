package com.ideais.stock.servlet.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
 
@WebFilter("/RequestLoggingFilter")
public class RequestLoggingFilter implements Filter {
 
    private ServletContext context;
     
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
    }
 
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Enumeration<String> params = req.getParameterNames();
        while(params.hasMoreElements()){
            String name = params.nextElement();
            String value = request.getParameter(name);
        }

        chain.doFilter(request, response);
    }
 
    public void destroy() {
    }
 
}
