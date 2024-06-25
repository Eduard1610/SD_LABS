package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final static String db = "lab09";
    private final static String user = "root";
    private final static String password = "root123";
    private final static String url = "jdbc:mariadb://localhost:3306/" + db;

    public static Connection getConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            if (conn != null)
                System.out.println("Conexión a la base de datos " + db + " ... Ok");
            
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
