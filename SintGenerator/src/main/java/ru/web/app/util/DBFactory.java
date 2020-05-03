package ru.web.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DBFactory {
    private static final Logger logger = LoggerFactory.getLogger(DBFactory.class);

    /**
     * property variable contains DB parameters.
     */
    private static Properties prop;

    static {
        prop = new Properties();
        String path = File.separator + "db.properties";
        InputStream in = DBFactory.class
                            .getClassLoader()
                                    .getResourceAsStream(path);
        try {
            prop.load(in);
        } catch (Exception e) {
            logger.error("Error with loading database properties", e);
        }
    }

    /**
     * returns DB connection.
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
//        Class.forName(prop.getProperty("classDriver"));
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection con = DriverManager.getConnection(
                prop.getProperty("url"),
                prop.getProperty("user"),
                prop.getProperty("password"));

        return con;
    }

    private DBFactory(){}
}

