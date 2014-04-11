package com.ideais.stock.servlet.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;

import com.ideais.stock.dao.AdminDao;
import com.ideais.stock.domain.Admin;

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
		
		Admin admin = new Admin();
		admin.setEmail(request.getParameter("email"));
		admin.setPassword(request.getParameter("pwd"));

		if (adminDao.authorize(admin)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", admin);
			session.setMaxInactiveInterval(30 * 60); // setting session to
														// expiry in 30 mins
			response.sendRedirect("/EstagioEstoque/web");
		} else {
			response.sendRedirect("/EstagioEstoque/index.jsp?error=true");
		}
	}

}
