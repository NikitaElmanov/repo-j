package ru.lv2.serv.servlets;

import ru.lv2.serv.accounts.AccountService;
import ru.lv2.serv.accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class SignUpServlet extends HttpServlet {

    private final String EMAIL = "@EMAIL";
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    //sign up
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (Objects.isNull(login)
                || Objects.isNull(password)){

            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile userProfile = new UserProfile(login, password, EMAIL);

        accountService.addNewUser(userProfile);

        accountService.addSession(req.getSession().getId(), userProfile);
//        resp.setContentType("text/html;charset=utf-8");
//        resp.getWriter().println("<a href='signIn.html'>To signIn page</a>");
//        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
