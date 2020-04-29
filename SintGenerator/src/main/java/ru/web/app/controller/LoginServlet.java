package ru.web.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp) throws IOException {
        logger.info("User login");

        req.getSession().setAttribute("username",
                                      req.getParameter("username").trim());
        req.getSession().setAttribute("password",
                                      req.getParameter("password").trim());
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect("/view/welcome.jsp");
    }
}
