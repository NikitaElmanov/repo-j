package ru.web.app.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

//@WebFilter(urlPatterns = "/view/welcome.jsp")
public class WelcomeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (Objects.isNull(request.getSession().getAttribute("username"))){
            request.setAttribute("message", "You have to login before visiting welcome page");
            request.getRequestDispatcher("/view/login.jsp").forward(request, response);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
