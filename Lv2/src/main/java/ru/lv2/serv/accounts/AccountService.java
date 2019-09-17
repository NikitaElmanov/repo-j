package ru.lv2.serv.accounts;

import wrap.jdbc.elem.User;
import wrap.jdbc.service.DBService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AccountService {

    private DBService service;

    public AccountService(DBService service) {
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
