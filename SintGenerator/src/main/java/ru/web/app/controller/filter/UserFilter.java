package ru.web.app.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

//@WebFilter(urlPatterns = {"/registration", "/login"})
public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String username = request.getParameter("username").trim();
        String password= request.getParameter("password").trim();

        if (Objects.isNull(username) || Objects.isNull(password)
            || username.equalsIgnoreCase("") || password.equalsIgnoreCase("")){

            request.setAttribute("message", "Invalid Name or Password");

            if (request.getRequestURI().equalsIgnoreCase("/login")){
                request.getRequestDispatcher("/view/login.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("/view/registration.jsp").include(request, response);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
