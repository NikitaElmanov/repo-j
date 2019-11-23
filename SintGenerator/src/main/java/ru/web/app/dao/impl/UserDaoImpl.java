package ru.web.app.dao.impl;

import ru.web.app.dao.UserDao;
import ru.web.app.dao.exception.ExceptionDao;
import ru.web.app.model.User;
import ru.web.app.util.DBFactory;

import java.sql.*;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public Integer createUser(String login, String password) {

        String insertStr = "insert into users (login, password) values (?,?)";
        ResultSet rs = null;
        Integer generatedKey = null;

        try(Connection conn = DBFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertStr, Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1, login);
            ps.setString(2, password);

            if (ps.executeUpdate() == 1){
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    generatedKey = rs.getInt(1);
                } else {
                    throw new ExceptionDao("Error with getting user's generated key with login - " + login);
                }
            } else {
                throw new ExceptionDao("Error with creating new user with login - " + login);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return generatedKey;
        }
    }

    @Override
    public User getUserById(Integer id) {
        String selectStr = "select * from users where id = ?";
        ResultSet rs = null;
        User user = null;

        try(Connection conn = DBFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(selectStr)){

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()){
                user = new User();

                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
            } else {
                throw new ExceptionDao("Error with getting user by id " + id);
            }

        } catch (SQLException | ExceptionDao ex){
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return user;
        }
    }

    @Override
    public List<User> getAllUsers() {
        String selectStr = "select * from users";
        ResultSet rs = null;
        List<User> users = null;
        User user = null;

        try(Connection conn = DBFactory.getConnection();
            Statement ps = conn.createStatement()){

            rs = ps.executeQuery(selectStr);

            while (rs.next()){
                user = new User();

                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));

                users.add(user);
            }

        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return users;
        }
    }
}
