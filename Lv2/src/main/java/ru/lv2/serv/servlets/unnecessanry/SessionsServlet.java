//package ru.lv2.serv.servlets.unnecessanry;
//
//import com.google.gson.Gson;
//import ru.lv2.serv.accounts.AccountService;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Objects;
//
//public class SessionsServlet extends HttpServlet {
//    private final AccountService accountService;
//
//    public SessionsServlet(AccountService accountService) {
//        this.accountService = accountService;
//    }
//
//
//    //authorizing (sign up)
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String login = req.getParameter("login");
//        String pass = req.getParameter("pass");
//
//        if (login == null || pass == null) {
//            resp.setContentType("text/html;charset=utf-8");
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            return;
//        }
//
//        UserProfile userProfile = accountService.getUserByLoginAndPass(login);
//        if (userProfile == null || !userProfile.getPass().equals(pass)) {
//            resp.setContentType("text/html;charset=utf-8");
//            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//
//        accountService.addSession(req.getSession().getId(), userProfile);
//        Gson gson = new Gson();
//        String json = gson.toJson(userProfile);
//        resp.setContentType("text/html;charset=utf-8");
//        resp.getWriter().println(json);
//        resp.setStatus(HttpServletResponse.SC_OK);
//    }
//
//
//    //sign in
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        UserProfile userProfile = accountService.getUserBySessionId(req.getSession().getId());
//
//        if (Objects.isNull(userProfile)){
//            resp.setContentType("text/html;charset=utf-8");
//            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//
//        Gson gson = new Gson();
//        String json = gson.toJson(userProfile);
//        resp.setContentType("text/html;charset=utf-8");
//        resp.getWriter().println(json);
//        resp.setStatus(HttpServletResponse.SC_OK);
//    }
//
//    //sign out
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String sessionId = req.getSession().getId();
//        UserProfile userProfile = accountService.getUserBySessionId(sessionId);
//
//        if (Objects.isNull(userProfile)){
//            resp.setContentType("text/html;charset=utf-8");
//            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        } else {
//            accountService.deleteSession(sessionId);
//            resp.setContentType("text/html;charset=utf-8");
//            resp.getWriter().println("GoodBye!");
//            resp.setStatus(HttpServletResponse.SC_OK);
//        }
//    }
//}
