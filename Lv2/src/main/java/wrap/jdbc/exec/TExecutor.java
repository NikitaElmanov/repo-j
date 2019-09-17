package wrap.jdbc.exec;

import wrap.jdbc.handler.TResultHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TExecutor {
    private static Connection connection;

    public TExecutor(Connection connection) {
        this.connection = connection;
    }

    public static int execUpdate(String sqlQuery) throws SQLException {
        Statement st = connection.createStatement();
        st.execute(sqlQuery);
        int rowNum = st.getUpdateCount();
        st.close();
        return rowNum;
    }

    public static <T> T execQuery(String sqlQuery, TResultHandler<T> handler) throws SQLException {
        Statement st = connection.createStatement();
        st.execute(sqlQuery);
        ResultSet rs = st.getResultSet();
        T value = handler.handle(rs);
        rs.close();
        st.close();
        return value;
    }
}
