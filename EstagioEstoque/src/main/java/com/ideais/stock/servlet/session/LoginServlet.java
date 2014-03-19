package com.ideais.stock.servlet.session;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ideais.stock.dao.AdminDao;
 
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AdminDao adminDao = new AdminDao();
 
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");
        
        if(adminDao.autorized(email, pwd)){
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            session.setMaxInactiveInterval(30*60); //setting session to expiry in 30 mins
            Cookie userName = new Cookie("email", email);
            userName.setMaxAge(30*60);
            response.addCookie(userName);
            response.sendRedirect("/EstagioEstoque/web");
        }else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("EstagioEstoque");
            rd.include(request, response);
        }
 
    }
 
}
