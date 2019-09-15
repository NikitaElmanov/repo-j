package wrap.jdbc.dao;

import wrap.jdbc.elem.Man;
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
            connection = DriverManager.getConnection("jdbc:h2:./mydb", "sa", "");

            executor = new TExecutor(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void createTable(String sqlQuery) throws SQLException {
        if (sqlQuery.startsWith("create")
                && sqlQuery.contains("table")){

            executor.execUpdate(sqlQuery);
        } else {
            throw new SQLException("Invalid sql query to create table");
        }
    }

    public int insertIntoTable(String sqlQuery) throws SQLException {
        if (sqlQuery.startsWith("insert")
                && sqlQuery.contains("into")){

            int count = executor.execUpdate(sqlQuery);

            if (count <= 0){
                throw new SQLException("Error with inserting table");
            } else {
                return count;
            }
        } else {
            throw new SQLException("Invalid sql query to insert into table");
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

    public Man getManById(long id) throws SQLException {
        return executor.execQuery("select * from men where id = " + id
                , rs -> {
                    Man man = null;

                    if (rs.next()){
                        man = new Man();

                        man.setId(rs.getInt("id"));
                        man.setName(rs.getString("name"));
                        man.setMarried(rs.getString("married"));
                    }
                    return man;
                });
    }

    public List<Man> getAllElems(String sqlQuery) throws SQLException {
        return executor.execQuery(sqlQuery, rs -> {
           List<Man> men = new ArrayList<>();
           while(rs.next()){
               Man man = new Man();
               man.setId(rs.getInt("id"));
               man.setName(rs.getString("name"));
               man.setMarried(rs.getString("married"));
               men.add(man);
           }
           return men;
        });
    }

    public int truncateAllData(String tableName) throws SQLException {
        return executor.execUpdate("truncate table " + tableName);
    }
}
