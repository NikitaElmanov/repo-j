package ru.web.app.util;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBFactory {
    private static Properties prop;

    static
    {
        prop = new Properties();
        String path = File.separator + "db.properties";
        InputStream in = DBFactory.class.getClassLoader().getResourceAsStream(path);
        try
        {
            prop.load(in);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
//        Class.forName(prop.getProperty("classDriver"));
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection con = DriverManager.getConnection(
                prop.getProperty("url"),
                prop.getProperty("user"),
                prop.getProperty("password"));

        return con;
    }


}
