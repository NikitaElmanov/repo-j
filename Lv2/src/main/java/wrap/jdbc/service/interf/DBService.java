package wrap.jdbc.service.interf;

import wrap.jdbc.elem.User;

import java.sql.SQLException;

public interface DBService {
    User getUser(String login, String password) throws SQLException;
    int addUser(String login, String password) throws SQLException;
}
