package ru.web.app.controller;

import ru.web.app.util.file.system.FileUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(urlPatterns = "/downloadSQL")
public class DownloadSQLFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html");
        resp.setHeader("Content-Disposition",
                           "attachment;filename=script.sql");

        String absPathTmp = FileUtils.createAndFillTMPFile(req.getParameter("script").trim());

        InputStream is = new FileInputStream(absPathTmp);
        OutputStream os = resp.getOutputStream();

        FileUtils.write(is, os);
    }
}
