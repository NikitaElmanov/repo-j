package ru.pro.test.cookies;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GetServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String[] cookiesNames = {"user", "user2"};
        List<Cookie> cookieArr = new ArrayList<>();

        if (cookies != null) {
            for (Cookie c : cookies) {
                for (String name : cookiesNames) {
                    if (name.equals(c.getName())) {
                        cookieArr.add(c);
                    }
                }
            }
        }

        PrintWriter out = response.getWriter();

        out.write("<html>");
        out.write("<head>");
        out.write("<title>GetServlet</title>");
        out.write("</head>");
        out.write("<body>");

        try{
            for (Cookie str : cookieArr) {
                out.write("<h3>Session id: " + request.getSession().getId() + "</h3>");
                out.write("<h3>Name: " + str.getName() + "</h3>");
                out.write("<h3>Value: " + str.getValue() + "</h3>");
                out.write("<h3>Comment: " + str.getComment() + "</h3>");
                out.write("<h3>Version: " + str.getVersion() + "</h3>");
                out.write("<hr>");
            }
        } finally {
            out.close();
        }
        out.write("</body>");
        out.write("</html>");
    }
}
