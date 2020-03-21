package ru.web.app.service;

import ru.web.app.dao.UserDao;
import ru.web.app.dao.exception.DAOException;
import ru.web.app.dao.impl.UserDaoImpl;
import ru.web.app.model.User;

import java.util.List;

final public class UserService {

    private static UserService instance = null;

    private UserService() {

    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }

        return instance;
    }

    public Integer createUser(final User user) {
        UserDao dao = UserDaoImpl.getInstance();
        Integer id = null;

        try {
            id = dao.createUser(user);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return id;
    }

    public User getUserById(final Integer id) {
        UserDao dao = UserDaoImpl.getInstance();
        User user = null;

        try {
            user = dao.getUserById(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return user;
    }

    public List<User> getAllUsers() {
        UserDao dao = UserDaoImpl.getInstance();
        List<User> users = null;

        try {
            users = dao.getAllUsers();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return users;
    }
}
