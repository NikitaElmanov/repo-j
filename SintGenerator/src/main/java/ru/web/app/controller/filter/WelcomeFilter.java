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

//@WebFilter(urlPatterns = "/view/welcome.jsp")
public class WelcomeFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (Objects.isNull(request.getSession().getAttribute("username"))) {
            request.setAttribute("message",
                                 "Необходимо выполнить \'Логин\' прежде чем заходить на главную строницу");
            response.setCharacterEncoding("utf-8");
            request.getRequestDispatcher("/view/login.jsp")
                    .forward(request, response);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(final FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }
}
