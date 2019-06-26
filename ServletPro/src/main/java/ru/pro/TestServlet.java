package ru.pro;

import ru.pro.elem.ObjectTest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        try {
//            Double param = Double.valueOf(request.getParameter("p"));

            out.write("<html>");
            out.write("<head>");
            out.write("<title>Simple Servlet for testing</title>");
            out.write("<link rel='stylesheet' href='"+ request.getContextPath() +"/css/style.css'/>");
            out.write("</head>");
            out.write("</body>");

            out.write("<h2>Servlet simple for example!</h2>");
//            out.write("<h2>Param = "+ param +"</h2>");

            request.setAttribute("hello", "Hello World!");

            getServletContext().setAttribute("obj", new ObjectTest("TestName"));

            out.write("</body>");
        } catch (Exception ex){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        finally {
            out.write("</body>");
            out.write("</html>");
            out.close();
        }

    }
}
