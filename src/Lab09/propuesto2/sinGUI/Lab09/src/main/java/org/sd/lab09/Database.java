package org.sd.lab09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final static String bd = "bd";
    private final static String login = "user";
    private final static String password = "pass";
    private final static String url = "jdbc:mysql://0.0.0.0:3306/" + bd;
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, login, password);
            if (conn != null) {
                System.out.println("Conectado a la base de datos [" + bd + "]");
            }
            return conn;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}