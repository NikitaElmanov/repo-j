package ru.web.app.controller.filter;

import ru.web.app.model.User;
import ru.web.app.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebFilter(urlPatterns = "/login")
public class LoginFilter implements Filter {
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

        String login = request.getParameter("username").trim();
        String password= request.getParameter("password").trim();

        UserService service = UserService.getInstance();

        List<User> users = service.getAllUsers();

        boolean flag = false;

        for (User user : users){
            if (user.getLogin().equalsIgnoreCase(login) && user.getPassword().equalsIgnoreCase(password)){
                flag = true;
            }
        }

        if (flag == false){
            request.setAttribute("message", "User with such login and password has no registered");
            request.getRequestDispatcher("/view/login.jsp").include(request, response);
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
