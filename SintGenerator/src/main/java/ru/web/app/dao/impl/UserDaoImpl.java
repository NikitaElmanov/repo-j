package ru.web.app.dao.impl;

import ru.web.app.dao.UserDao;
import ru.web.app.dao.exception.DAOException;
import ru.web.app.model.User;
import ru.web.app.util.DBFactory;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
        User user;

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

    @Override
    public void updateUserName(final String login,
                               final String password,
                               final String newLogin) throws DAOException {
        try (Connection conn = DBFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(""
                            + "update users                     \n"
                            + "set login = ?                    \n"
                            + "where id = ?                     \n")) {

            ps.setString(1, newLogin);
            ps.setInt(2, getIdByLoginAndPassword(login, password));

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("cannot update user login with login = " + login, e);
        }
    }

    @Override
    public void updateUserPassword(final String login,
                                   final String password,
                                   final String newPassword) throws DAOException {
        try (Connection conn = DBFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(""
                              + "update users                     \n"
                              + "set password = ?                 \n"
                              + "where id = ?                     \n")) {

            ps.setString(1, newPassword);
            ps.setInt(2, getIdByLoginAndPassword(login, password));

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("cannot update user password with login = " + login, e);
        }
    }

    private static int getIdByLoginAndPassword(String login,
                                               String password) throws DAOException {
        ResultSet rs = null;
        int resId;

        try (Connection conn = DBFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("select id from users where login = ? and password = ?")) {

            ps.setString(1, login);
            ps.setString(2, password);

            rs = ps.executeQuery();
            rs.next();

            resId = rs.getInt(1);

        } catch (SQLException e) {
            throw new DAOException("cannot get user id by login and password", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return resId;
    }
}
