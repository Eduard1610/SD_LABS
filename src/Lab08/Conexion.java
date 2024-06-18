package Lab08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private String dbUrl;
    private String user;
    private String password;
    private Connection connection;

    public Conexion(String dbUrl, String user, String password) {
        this.dbUrl = dbUrl;
        this.user = user;
        this.password = password;
    }

    public Connection connect() {
        try {
            // Establecer la conexión
            connection = DriverManager.getConnection(dbUrl, user, password);
            System.out.println("Connection to MySQL DB successful");
        } catch (SQLException e) {
            System.out.println("The error '" + e.getMessage() + "' occurred");
        }
        return connection;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("The connection is closed");
            } catch (SQLException e) {
                System.out.println("The error '" + e.getMessage() + "' occurred while closing the connection");
            }
        }
    }
}