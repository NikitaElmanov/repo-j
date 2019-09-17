import wrap.jdbc.elem.User;
import wrap.jdbc.service.DBService;

import java.sql.SQLException;
import java.util.List;

public class Main {
//    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost/productsdb?serverTimezone=UTC";
//
//    //  Database credentials
//    static final String USER = "root";
//    static final String PASS = "lololo98";

    public static void main(String[] args) throws SQLException {
        DBService service = new DBService();
//        service.cleanUp("men");
//        service.printInfo();
//        service.createUserTable();

//        service.addUser("insert into men (name, married) values ('tom', 'a'),('ann', 'p'),('lola', 'p')");
//        User user = service.getUser(2);
//        System.out.println(user);
//        service.removeMan("delete from men where id = " + 80);
//
//        List<User> men = service.getAll("select * from men");
//
//        men.stream().forEach(m -> {
//            System.out.println(m);
//        });
    }
}
