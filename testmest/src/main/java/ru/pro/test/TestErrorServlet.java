package ru.pro.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TestErrorServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        out.write("<html>");
        out.write("<head>");
        out.write("<title>Calc Servlet</title>");
//        out.write("<link rel='stylesheet' href='style/ei.css'/>");
        out.write("</head>");
        out.write("<body>");


        out.write("<h1>Hey ! I love you!</h2>");


        out.write("</body>");
        out.write("</html>");


    }
}
