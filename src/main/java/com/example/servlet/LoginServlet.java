package com.example.servlet;

import com.example.Users;

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
            HttpSession session = req.getSession(false);
            String urlJsp;
            if (session == null || session.getAttribute("user") == null) {
                urlJsp = "/login.jsp";
            } else {
                urlJsp = "/user/hello.jsp";
            }
            req.getRequestDispatcher(urlJsp).forward(req, resp);
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String urlJsp;
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            Users users = Users.getInstance();

            if (users.getUsers().contains(login) && !password.equals("")) {
                HttpSession session = req.getSession();
                session.setAttribute("user", login);
                urlJsp = req.getContextPath() + "/user/hello.jsp";
            } else {
                urlJsp = req.getContextPath() + "/login.jsp";
            }
            resp.sendRedirect(urlJsp);
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }
}
