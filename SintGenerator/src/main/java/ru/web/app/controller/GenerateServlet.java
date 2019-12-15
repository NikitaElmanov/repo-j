package ru.web.app.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/generate")
public class GenerateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("resParams").trim());
        System.out.println(req.getParameter("fieldNames"));
        System.out.println(req.getParameter("fieldTypes"));
        System.out.println(req.getParameter("fieldPrecisions"));
        System.out.println(req.getParameter("fieldPK"));
    }
}
