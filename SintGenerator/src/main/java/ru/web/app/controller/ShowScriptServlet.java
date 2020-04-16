package ru.web.app.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/showScript")
public class ShowScriptServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("view/generated-script.jsp")
                .forward(req, resp);
        //resp.getWriter().write(String.valueOf(req.getSession().getAttribute("res-script")));
    }
}
