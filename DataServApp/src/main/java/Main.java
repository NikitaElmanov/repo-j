import wrap.jdbc.elem.Man;
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
        service.printInfo();
        service.createTable("" +
                "create table if not exists Men (  \n" +
                "id int auto_increment             \n" +
                ", name varchar(256)               \n" +
                ", married varchar(256)            \n" +
                ", primary key (id),               \n" +
                "check (married in ('p', 'a'))      )");

        service.addMan("insert into men (name, married) values ('tom', 'a'),('ann', 'p'),('lola', 'p')");
        Man man = service.getMan(2);
        System.out.println(man);
        service.removeMan("delete from men where id = " + 80);

        List<Man> men = service.getAll("select * from men");

        men.stream().forEach(m -> {
            System.out.println(m);
        });
    }
}
