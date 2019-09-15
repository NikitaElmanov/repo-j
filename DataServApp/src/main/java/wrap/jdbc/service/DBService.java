package wrap.jdbc.service;

import wrap.jdbc.dao.Dao;
import wrap.jdbc.elem.Man;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBService {

    private Dao dao;
    private Connection connection;

    public DBService() {
        dao = new Dao();
        connection = dao.getConnection();
    }

    public Man getMan(long id) throws SQLException {
        try{
            return dao.getManById(id);
        }catch (SQLException e){
            throw new SQLException(e);
        }
    }

    public void createTable(String sqlQuery) throws SQLException {
        dao.createTable(sqlQuery);
    }

    public int addMan(String sqlQuery) throws SQLException {
        try {
            connection.setAutoCommit(false);
            int count = dao.insertIntoTable(sqlQuery);
            connection.commit();
            return count;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new SQLException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public int removeMan(String sqlQuery) throws SQLException {
        return dao.deleteFromTable(sqlQuery);
    }

    public void printInfo(){
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
            System.out.println("UserName: " + connection.getMetaData().getUserName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Man> getAll(String sqlQuery) throws SQLException {
        try{
            connection.setAutoCommit(false);
            List<Man> men = dao.getAllElems(sqlQuery);
            connection.commit();
            return men;
        }catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new SQLException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public int cleanUp(String tableName) throws SQLException {
        try{
            return dao.truncateAllData(tableName);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
