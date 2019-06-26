package ru.pro.test.cookies;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SetServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        try{
            Cookie cookie = new Cookie("user", "Ann");
            cookie.setComment("Hello, It's comment!");
//            cookie.setValue("Fuck! it's value!");
            cookie.setVersion(23);

            response.addCookie(cookie);

            Cookie cookie2 = new Cookie("user2", "Tom");
            cookie.setComment("Hello, It's comment! Tom");
//            cookie.setValue("Fuck! it's value! Tom");
            cookie.setVersion(1);

            response.addCookie(cookie2);
            out.write("Cookie is set!");
        } finally {
            out.close();
        }
    }
}
