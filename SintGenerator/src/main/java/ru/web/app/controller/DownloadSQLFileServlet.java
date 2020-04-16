package ru.web.app.controller;

import ru.web.app.util.file.system.FileUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//@WebServlet(urlPatterns = "/downloadSQL")
public class DownloadSQLFileServlet extends HttpServlet {
    @Override
    protected final void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp) throws IOException {

        if (req.getSession().getAttribute("username") != null
            && req.getParameter("script") != null) {

            resp.setContentType("text/html");
            resp.setHeader("Content-Disposition",
                           "attachment;filename=script.sql");

            String absPathTmp = FileUtils
                    .createAndFillTMPFile(req.getParameter("script")
                                                  .trim());

            InputStream is = new FileInputStream(absPathTmp);
            OutputStream os = resp.getOutputStream();

            FileUtils.write(is, os);
        } else {
            //TODO maybe will be needed to be fix
            resp.sendRedirect("/view/login.jsp");
        }
    }
}
