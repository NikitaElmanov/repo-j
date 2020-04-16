package ru.web.app.controller.filter;

import ru.web.app.model.User;
import ru.web.app.service.UserService;
import ru.web.app.util.CryptoUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterConfig;
import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebFilter(urlPatterns = "/registration")
public class RegFilter implements Filter {
    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String login = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        try {
            password = CryptoUtil
                    .byteArrayToHexString(CryptoUtil.computeHash(password));
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserService service = UserService.getInstance();
        List<User> users = service.getAllUsers();

        if (users != null) {
            for (User user : users) {
                if (user.getLogin().equalsIgnoreCase(login)) {
                    request.setAttribute("message",
                                         "Пользователь уже существует");
                    response.setCharacterEncoding("utf-8");
                    request.getRequestDispatcher("/view/registration.jsp")
                            .forward(request, response);
                    return;
                }
            }
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
