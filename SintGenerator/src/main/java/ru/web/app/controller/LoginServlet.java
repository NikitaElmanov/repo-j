package ru.web.app.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp) throws IOException {

        req.getSession().setAttribute("username",
                                      req.getParameter("username").trim());
        req.getSession().setAttribute("password",
                                      req.getParameter("password").trim());
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect("/view/welcome.jsp");
    }
}
