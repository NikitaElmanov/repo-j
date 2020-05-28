package ru.web.app.model.dao;

import ru.web.app.model.dao.exception.DAOException;
import ru.web.app.model.pojo.Entity;

import java.util.List;

public interface UserDao <T extends Entity> {
    /**
     * create new row with user's params in DB.
     * @param user
     * @return generated key
     * @throws DAOException
     */
    Integer createUser(T user) throws DAOException;

    /**
     * @param id
     * @return user by inputting id
     * @throws DAOException
     */
    T getUserById(Integer id) throws DAOException;

    /**
     * @return just all existing users
     * @throws DAOException
     */
    List<T> getAllUsers() throws DAOException;

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
