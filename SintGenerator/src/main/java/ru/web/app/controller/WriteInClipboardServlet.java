package ru.web.app.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;

@WebServlet("/WriteClipboard")
public class WriteInClipboardServlet extends HttpServlet {
    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp) throws ServletException, IOException {

        String script = req.getParameter("cb-text").trim();

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(script);
        clipboard.setContents(stringSelection, null);

        req.getRequestDispatcher("view/generated-script.jsp").forward(req, resp);
    }
}
