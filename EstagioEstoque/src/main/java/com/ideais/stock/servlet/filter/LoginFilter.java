package com.ideais.stock.servlet.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ideais.stock.dao.AdminDao;
import com.ideais.stock.domain.Admin;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		Cookie[] cookies = httpRequest.getCookies();
		
		AdminDao adminDao = new AdminDao();
		List<Admin> admins = adminDao.findAll();
		
		Boolean validator = false;
		
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ( "email".equals(cookie.getName()) ) {
					System.out.println(cookie.getName() + "," + cookie.getValue());
					for (Admin admin : admins) {
						if ( admin.getEmail().equals(cookie.getValue()) ) {
							validator = true;
							break;
						}
					}
				}
			}
		}
		
		if (validator) {
			chain.doFilter(request, response);
		} else {
			System.out.println("\n\n\nnegado\n\n\n");
			httpResponse.sendRedirect("/EstagioEstoque/index.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
