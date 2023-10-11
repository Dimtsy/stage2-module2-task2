package com.example.servlet;

import com.example.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            if(session == null || session.getAttribute("user") == null){
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
            }else {
                resp.sendRedirect(req.getContextPath() + "/user/hello.jsp");
            }
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            RequestDispatcher dispatcher;
            if(login == null || !Users.getInstance().getUsers().contains(login) ||
                    password.isEmpty()){
                dispatcher = req.getRequestDispatcher("/login.jsp");
                dispatcher.forward(req, resp);
            }else {
                req.getSession().setAttribute("user", login);
                resp.sendRedirect(req.getContextPath() + "/user/hello.jsp");
            }
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }
}
