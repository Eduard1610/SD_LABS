package Lab09.Cuestionario_Aplicativo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeTransaction {

    static final String DB_URL = "jdbc:mysql://localhost/sd";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false); // Inicia la transacción

            Statement stmt = conn.createStatement();
            // Operación de inserción
            stmt.executeUpdate("INSERT INTO Employees (name, age) VALUES ('Alice', 25)");
            // Operación de actualización
            stmt.executeUpdate("UPDATE Employees SET age = age + 1 WHERE name = 'Bob'");
            // Operación de eliminación
            stmt.executeUpdate("DELETE FROM Employees WHERE name = 'Charlie'");

            conn.commit(); // Confirma la transacción
            System.out.println("Transacción completada exitosamente.");
        } catch (SQLException se) {
            if (conn != null) {
                try {
                    conn.rollback(); // Deshace la transacción en caso de error
                    System.out.println("Transacción deshecha debido a un error.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            se.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

