package ru.web.app.controller;

import ru.web.app.model.User;
import ru.web.app.service.UserService;
import ru.web.app.util.CryptoUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/registration")
public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp) throws IOException {

        User user = new User();

        String password = req.getParameter("password").trim();

        try {
            password = CryptoUtil
                    .byteArrayToHexString(CryptoUtil.computeHash(password));
        } catch (Exception e) {
            e.printStackTrace();
        }

        user.setLogin(req.getParameter("username").trim());
        user.setPassword(password);

        UserService service = UserService.getInstance();
        service.createUser(user);

        resp.setStatus(HttpServletResponse.SC_OK);

        resp.sendRedirect("view/login.jsp");
    }
}
