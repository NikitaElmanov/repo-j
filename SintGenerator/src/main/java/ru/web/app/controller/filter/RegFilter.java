package ru.web.app.controller.filter;

import ru.web.app.model.User;
import ru.web.app.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebFilter(urlPatterns = "/registration")
public class RegFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String login = request.getParameter("username").trim();
        String password= request.getParameter("password").trim();

        UserService service = UserService.getInstance();
        List<User> users = service.getAllUsers();

        for (User user : users){
            if (user.getLogin().equalsIgnoreCase(login) && user.getPassword().equalsIgnoreCase(password)){
                request.setAttribute("message", "Пользователь уже существует");
                response.setCharacterEncoding("utf-8");
                request.getRequestDispatcher("/view/registration.jsp").forward(request, response);
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
