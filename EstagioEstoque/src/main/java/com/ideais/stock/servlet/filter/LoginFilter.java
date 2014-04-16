package com.ideais.stock.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.service.AdminService;

/**
 * Servlet Filter implementation class LoginFilter
 */

public class LoginFilter implements Filter {
	
	private static final Logger LOG = Logger.getLogger(LoginFilter.class); 

	@Autowired
	private AdminService adminService;
    
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
	
		if (httpRequest.getSession().getAttribute("user") != null) {
			chain.doFilter(request, response);
		} else {
			LOG.info("Acesso Negado");
			httpResponse.sendRedirect("/EstagioEstoque/index.jsp");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
