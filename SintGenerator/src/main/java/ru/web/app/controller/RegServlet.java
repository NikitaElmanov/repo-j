package ru.web.app.controller;

import ru.web.app.model.User;
import ru.web.app.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/registration")
public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User();
        user.setLogin(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));

        UserService service = UserService.getInstance();
        service.createUser(user);

        System.out.println("reg servlet");

        resp.sendRedirect("view/login.jsp");
    }
}
