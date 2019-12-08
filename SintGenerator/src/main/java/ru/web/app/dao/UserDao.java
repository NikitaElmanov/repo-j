package ru.web.app.dao;

import ru.web.app.dao.exception.DAOException;
import ru.web.app.model.User;

import java.util.List;

public interface UserDao {
    Integer createUser(User user) throws DAOException;
    User getUserById(Integer id) throws DAOException;
    List<User> getAllUsers() throws DAOException;
}
