package com.ideais.stock.servlet.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;

import com.ideais.stock.dao.AdminDao;

public class LoginServlet extends HttpServlet implements HttpRequestHandler {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AdminDao adminDao;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");

		if (adminDao.authorized(email, pwd)) {
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			session.setMaxInactiveInterval(30 * 60); // setting session to
														// expiry in 30 mins
			Cookie userName = new Cookie("email", email);
			userName.setMaxAge(30 * 60);
			response.addCookie(userName);
			response.sendRedirect("/EstagioEstoque/web");
		} else {
			response.sendRedirect("/EstagioEstoque/index.jsp?error=true");
		}
	}

}
