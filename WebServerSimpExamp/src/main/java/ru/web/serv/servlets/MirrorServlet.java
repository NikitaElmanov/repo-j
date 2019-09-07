package ru.web.serv.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MirrorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String key = req.getParameter("key");
//        Map<String, String> var = new HashMap<>();
//        var.put("mes", mes);

//        resp.getWriter().println(PageGenerator.instance().getPage("mirPage.jsp", var)); //2

        resp.getWriter().write(key);//1

        resp.setContentType("text/html;charset=uft-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
