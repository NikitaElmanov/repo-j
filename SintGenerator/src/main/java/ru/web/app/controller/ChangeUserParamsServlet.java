package ru.web.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.web.app.model.service.UserService;
import ru.web.app.util.CryptoUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(urlPatterns = "/changeUserParams")
public class ChangeUserParamsServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ChangeUserParamsServlet.class);

    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp) throws IOException {

        if (req.getSession().getAttribute("username") != null) {

            String userName = (String) req.getSession().getAttribute("username");
            String password = (String) req.getSession().getAttribute("password");

            String newName = req.getParameter("newName").trim();
            String newPassword = req.getParameter("newPassword").trim();

            UserService service = UserService.getInstance();
            /*List<User> users = service.getAllUsers();

            for (User user : users) {
                if (user.getLogin().equalsIgnoreCase(newName)
                        && user.getPassword().equalsIgnoreCase(newPassword)) {
                    req.setAttribute("message",
                                         "Пользователь уже существует");
                    resp.setCharacterEncoding("utf-8");
                    req.getRequestDispatcher("/view/welcome.jsp")
                            .forward(req, resp);
                    return;
                }
            }*/

            try {
                password = CryptoUtil
                        .byteArrayToHexString(CryptoUtil.computeHash(password));
            } catch (Exception e) {
                logger.error("Error with getting bytes array from password string", e);
            }

            if (!newName.equalsIgnoreCase("")) {
                service.updateUserName(userName, password, newName);
                logger.info("User's name has been changed");

                userName = newName;
                req.getSession().removeAttribute("username");
                req.getSession().invalidate();
                req.getSession().setAttribute("username", newName);
            }

            if (!newPassword.equalsIgnoreCase("")) {

                try {
                    newPassword = CryptoUtil
                            .byteArrayToHexString(CryptoUtil.computeHash(newPassword));
                } catch (Exception e) {
                    logger.error("Error with getting bytes array from password string", e);
                }

                service.updateUserPassword(userName, password, newPassword);
                logger.info("User's password has been changed");
            }
        }

        resp.sendRedirect("/view/login.jsp");
    }
}
