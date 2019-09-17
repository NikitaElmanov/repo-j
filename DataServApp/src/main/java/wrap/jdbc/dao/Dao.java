package wrap.jdbc.dao;

import wrap.jdbc.elem.User;
import wrap.jdbc.exec.TExecutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    private TExecutor executor;
    private Connection connection;

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:h2:./h2db", "test", "test");

            executor = new TExecutor(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void createUserTable() throws SQLException {
            executor.execUpdate("" +
                    "create table if not exists users \n" +
                    "(                                \n" +
                    "id bigint auto_increment,        \n" +
                    "login varchar(256),              \n" +
                    "password varchar(256),           \n" +
                    "primary key (id)                 \n" +
                    ")                                  ");
    }

    public int insertIntoUserTable(String login, String password) throws SQLException {

            String sqlQuery = "insert into users (login, password) values ('"+login+"', '"+password+"')";
            int count = executor.execUpdate(sqlQuery);

            if (count <= 0){
                throw new SQLException("Error with inserting into 'user' table");
            } else {
                return count;
            }
    }

    public int deleteFromTable(String sqlQuery) throws SQLException {
        if (sqlQuery.startsWith("delete")
                && sqlQuery.contains("from")){

            int count = executor.execUpdate(sqlQuery);

            if (count <= 0){
                System.out.println("element(s) was(were) not deleted");
            }

            return count;

        } else {
            throw new SQLException("Invalid sql query to delete from table");
        }
    }

    public User getUser(String login, String password) throws SQLException {
        return executor.execQuery("select * from users where login = " + login + " and password = " + password
                , rs -> {
                    User user = null;

                    if (rs.next()){
                        user = new User();

                        user.setId(rs.getInt("id"));
                        user.setLogin(rs.getString("login"));
                        user.setPassword(rs.getString("password"));
                    }
                    return user;
                });
    }

    public List<User> getAllElems(String sqlQuery) throws SQLException {
        return executor.execQuery(sqlQuery, rs -> {
           List<User> men = new ArrayList<>();
           while(rs.next()){
               User user = new User();
               user.setId(rs.getInt("id"));
               user.setLogin(rs.getString("name"));
               user.setPassword(rs.getString("married"));
               men.add(user);
           }
           return men;
        });
    }

    public int truncateAllData(String tableName) throws SQLException {
        return executor.execUpdate("truncate table " + tableName);
    }
}
