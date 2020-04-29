package ru.web.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;

//@WebServlet("/writeClipboard")
public class WriteInClipboardServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(WriteInClipboardServlet.class);

    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {

        logger.info("writing into clipboard started");
        String script = req.getParameter("cb-text").trim();

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(script);
        clipboard.setContents(stringSelection, null);

        logger.info("writing into clipboard finished successful");

        req.getRequestDispatcher("view/generated-script.jsp").forward(req, resp);
    }
}
