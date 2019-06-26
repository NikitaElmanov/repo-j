package ru.pro;

import ru.pro.elem.ObjectTest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        out.write("<html>");
        out.write("<head>");
        out.write("<title>Simple Servlet for testing</title>");
        out.write("<link rel='stylesheet' href='"+ request.getContextPath() +"/css/style.css'/>");
        out.write("</head>");
        out.write("</body>");

        out.write("<h2>Check Servlet!</h2>");
        out.write("<h3>"+ ((ObjectTest)getServletContext().getAttribute("obj")).getName() +"</h3>");

        out.write("</body>");

        out.write("</body>");
        out.write("</html>");
        out.close();
    }
}
