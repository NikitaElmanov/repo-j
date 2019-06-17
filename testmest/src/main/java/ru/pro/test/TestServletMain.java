package ru.pro.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class TestServletMain extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        Enumeration en = request.getParameterNames();

        out.write("<html><head><title>TestServletMain</title></head><body><ol>\n");
        while (en.hasMoreElements()){
            out.write("<li>"+ en.nextElement() +"</li>\n");
//            System.out.println(en.nextElement());
        }
        out.write("</ol></body></html>");

//        out.write("" +
//                "<html>" +
//                    "<head>" +
//                        "<title>TestServlet Two</title>" +
//                    "</head>" +
//                    "<body>" +
//                        "<img alt='fff' style='width: 200px; border-radius: 10px;' src='images/chroma.jpeg'/>" +
//                    "</body>" +
//                "</html>");

        out.close();
    }
}
