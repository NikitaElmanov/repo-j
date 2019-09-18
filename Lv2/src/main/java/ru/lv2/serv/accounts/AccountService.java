package ru.lv2.serv.accounts;

import wrap.jdbc.elem.User;
import wrap.jdbc.service.DBServiceImpl;
import wrap.jdbc.service.interf.DBService;

import java.sql.SQLException;

public class AccountService {

    private DBService service;

    public AccountService(DBServiceImpl service) {
        this.service = service;
    }

    public void addNewUser(User user) {
        try {
            service.addUser(user.getLogin(), user.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByLoginAndPass(String login, String password) {
        User user = null;
        try {
            user =  service.getUser(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return user;
        }
    }
}
