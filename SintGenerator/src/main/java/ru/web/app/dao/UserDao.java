package ru.web.app.dao;

import ru.web.app.model.User;

import java.util.List;

public interface UserDao {
    Integer createUser(String login, String password) throws Exception;
    User getUserById(Integer id);
    List<User> getAllUsers();
}
