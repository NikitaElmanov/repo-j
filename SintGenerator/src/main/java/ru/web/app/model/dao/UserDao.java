package ru.web.app.model.dao;

import ru.web.app.model.dao.exception.DAOException;
import ru.web.app.model.pojo.User;

import java.util.List;

public interface UserDao {
    /**
     * create new row with user's params in DB.
     * @param user
     * @return generated key
     * @throws DAOException
     */
    Integer createUser(User user) throws DAOException;

    /**
     * @param id
     * @return user by inputting id
     * @throws DAOException
     */
    User getUserById(Integer id) throws DAOException;

    /**
     * @return just all existing users
     * @throws DAOException
     */
    List<User> getAllUsers() throws DAOException;

    /**
     * changes user's param (login).
     * @param login
     * @param password
     * @param newLogin
     * @throws DAOException
     */
    void updateUserName(String login, String password, String newLogin)
            throws DAOException;

    /**
     * changes user's param (password).
     * @param login
     * @param password
     * @param newPassword
     * @throws DAOException
     */
    void updateUserPassword(String login, String password, String newPassword)
            throws DAOException;
}
