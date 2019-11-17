package ru.web.app.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(urlPatterns = {"/view/registration", "/login"})
public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String username = request.getParameter("username");
        String password = request.getParameter("password");


        if (Objects.nonNull(username) && Objects.nonNull(password)
            && !username.equalsIgnoreCase("") && !password.equalsIgnoreCase("")){
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            request.setAttribute("message", "Invalid Name or Password");

            String pageError;
            if (request.getRequestURI().contains("view")){
                pageError = "error.jsp";
            } else {
                pageError = "view/error.jsp";
            }

            request.getRequestDispatcher(pageError).forward(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
