package ru.web.serv.servlets;

import ru.web.serv.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AllRequestsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Object> varMap = createPageVarMap(req);
        varMap.put("message", "");

        resp.getWriter().println(PageGenerator.instance().getPage("page.html", varMap));

        resp.setContentType("text/html;charset=uft-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private Map<String, Object> createPageVarMap(HttpServletRequest req) {
        Map<String, Object> varMap = new HashMap<>();
        varMap.put("method", req.getMethod());
        varMap.put("URL", req.getRequestURL().toString());
        varMap.put("pathInfo", req.getPathInfo());
        varMap.put("sessionId", req.getSession().getId());
        varMap.put("parameters", req.getParameterMap().toString());

        return varMap;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> varMap = createPageVarMap(req);

        String message = req.getParameter("message");

        resp.setContentType("text/html;charset=utf-8");

        if (Objects.nonNull(message)){
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

        varMap.put("message", message);

        resp.getWriter().println(PageGenerator.instance().getPage("page.html", varMap));
    }
}
