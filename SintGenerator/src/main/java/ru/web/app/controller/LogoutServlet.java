package ru.web.app.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession();
        session.removeAttribute("username");
        session.invalidate();

        resp.sendRedirect("/view/login.jsp");

    }
}
