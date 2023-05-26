package com.example.filter.servlet;

import com.example.Users;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String urlJsp = session.getAttribute("user") == null ? "/login.jsp" : "/user/hello.jsp";
        getServletContext().getRequestDispatcher(urlJsp).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Users users = Users.getInstance();
        String urlJsp = "/login.jsp";
        if (users.getUsers().contains(login) && password != null) {
            urlJsp = "/user/hello.jsp";
            req.getSession().setAttribute("user", 1);
        }
        getServletContext().getRequestDispatcher(urlJsp).forward(req, resp);
    }
}
