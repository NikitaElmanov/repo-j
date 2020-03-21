package ru.web.app.dao.impl;

import ru.web.app.dao.UserDao;
import ru.web.app.dao.exception.DAOException;
import ru.web.app.model.User;
import ru.web.app.util.DBFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

final public class UserDaoImpl implements UserDao {

    private static UserDao instance;

    private UserDaoImpl() {
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }

        return instance;
    }

    @Override
    public Integer createUser(final User user) throws DAOException {

        String insertStr = "insert into users (login, password) values (?,?)";
        ResultSet rs = null;
        Integer generatedKey;

        try (Connection conn = DBFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertStr,
                                                         Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());

            if (ps.executeUpdate() == 1) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    generatedKey = rs.getInt(1);
                } else {
                    throw new DAOException("Error with getting user's generated key with login - " + user.getLogin());
                }
            } else {
                throw new DAOException("Error with creating new user with login - " + user.getLogin());
            }

            return generatedKey;

        } catch (SQLException ex) {
            throw new DAOException("cannot create new user with login - " + user.getLogin(), ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public User getUserById(final Integer id) throws DAOException {
        String selectStr = "select * from users where id = ?";
        ResultSet rs = null;
        User user = null;

        try (Connection conn = DBFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(selectStr)) {

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();

                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
            } else {
                throw new DAOException("Error with getting user by id " + id);
            }

            return user;

        } catch (SQLException | DAOException ex) {
            throw new DAOException("cannot get user by id - " + id, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<User> getAllUsers() throws DAOException {
        String selectStr = "select * from users";
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        User user;

        try (Connection conn = DBFactory.getConnection();
            Statement ps = conn.createStatement()) {

            rs = ps.executeQuery(selectStr);

            while (rs.next()) {
                user = new User();

                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));

                users.add(user);
            }

            return users;

        } catch (SQLException ex) {
            throw new DAOException("cannot get all users", ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
