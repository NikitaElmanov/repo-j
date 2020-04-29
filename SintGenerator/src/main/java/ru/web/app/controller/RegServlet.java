package ru.web.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.web.app.model.pojo.User;
import ru.web.app.model.service.UserService;
import ru.web.app.util.CryptoUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(urlPatterns = "/registration")
public class RegServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RegServlet.class);
    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp) throws IOException {

        User user = new User();

        String password = req.getParameter("password").trim();

        try {
            password = CryptoUtil
                    .byteArrayToHexString(CryptoUtil.computeHash(password));
        } catch (Exception e) {
            logger.error("Error with getting bytes array from password string", e);
        }

        user.setLogin(req.getParameter("username").trim());
        user.setPassword(password);

        UserService service = UserService.getInstance();
        service.createUser(user);

        resp.setStatus(HttpServletResponse.SC_OK);

        resp.sendRedirect("view/login.jsp");
    }
}
