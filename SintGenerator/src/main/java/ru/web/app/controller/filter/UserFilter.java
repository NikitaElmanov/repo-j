package ru.web.app.controller.filter;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.FilterChain;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

//@WebFilter(urlPatterns = {"/registration", "/login"})
public class UserFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        if (Objects.isNull(username)
            || Objects.isNull(password)
            || username.equalsIgnoreCase("")
            || password.equalsIgnoreCase("")) {

            request.setAttribute("message", "Неверный(е) логин, пароль");
            response.setCharacterEncoding("utf-8");

            if (request.getRequestURI().equalsIgnoreCase("/login")) {
                request.getRequestDispatcher("/view/login.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("/view/registration.jsp").include(request, response);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(final FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }
}
