package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/user/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession(false);
        try {
            String urlJsp = session.getAttribute("user") == null ? "/login.jsp" : "/user/hello.jsp";
            httpServletRequest.getServletContext().getRequestDispatcher(urlJsp).forward(request, response);

        } catch (NullPointerException e) {
            httpServletRequest.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}