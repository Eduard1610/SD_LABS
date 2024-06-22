package Lab08;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.jdbc.CallableStatement;

public class app {

    // Funcion para limpiar consola
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error mientras se limpia la consola: " + ex.getMessage());
        }
    }
    
    public static void menuPrincipal(Connection conn) throws SQLException {
        int opt = 0;
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("***BIENVENIDO***");
            System.out.println("1. Gestionar Tablas");
            System.out.println("2. Insertar Departamento");
            System.out.println("3. Asignar ingeniero a proyecto");
            System.out.println("4. Salir");
            System.out.print("OPCION: ");
            opt = sc.nextInt();
            sc.nextLine(); //Buffer fix
            clearScreen();

            if(opt == 4) {
                System.out.println("Adios!!!!");
                break;
            }
            switch(opt) {
                case 1: gestionarTablasMenu(conn); break;
                case 2: insertarDepartamento(conn); break;
                case 3: asignarIngenieroAProyecto(conn); break;
            }
        }
        
    }

    public static void asignarIngenieroAProyecto(Connection conn) throws SQLException {
        // Preparar la llamada al procedimiento almacenado
        int idProy;
        int idIng;
        Scanner sc = new Scanner(System.in);
        System.out.print("Id Proyecto: "); idProy = sc.nextInt();
        sc.nextLine();
        System.out.print("Id Ingeniero: "); idIng = sc.nextInt();
        sc.nextLine();

        String sql = "{CALL sp_AsignarIngenieroAProyecto(?, ?)}";
        try (CallableStatement stmt = (CallableStatement) conn.prepareCall(sql)) {
            // Establecer los parámetros de entrada
            stmt.setInt(1, idProy);
            stmt.setInt(2, idIng);
            // Ejecutar el procedimiento almacenado
            stmt.execute();
            System.out.println("Ingeniero asignado al proyecto exitosamente.");
        }
        
    }

    private static void insertarDepartamento(Connection conn) throws SQLException {
        int id;
        String nombre;
        String telefono;
        String fax;
        Scanner sc = new Scanner(System.in);
        System.out.print("IDDpto: "); id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nombre: "); nombre = sc.nextLine();
        System.out.print("Telefono: "); telefono = sc.nextLine();
        System.out.print("Fax: "); fax = sc.nextLine();

        String sql = "{CALL sp_InsertarDepartamento(?, ?, ?, ?)}";
        try (CallableStatement stmt = (CallableStatement) conn.prepareCall(sql)) {
            // Establecer los parámetros de entrada
            stmt.setInt(1, id);
            stmt.setString(2, nombre);
            stmt.setString(3, telefono);
            stmt.setString(4, fax);

            // Ejecutar el procedimiento almacenado
            stmt.execute();
            System.out.println("Procedimiento almacenado ejecutado exitosamente.");
        }
        
    }

    

    private static void gestionarTablasMenu(Connection conn) {
        int opt = 0;
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("***GESTIONAR TABLAS***");
            System.out.println("1. Insertar");
            System.out.println("2. Actualizar");
            System.out.println("3. Eliminar");
            System.out.println("4. <--- Atras");
            System.out.print("OPCION: ");
            opt = sc.nextInt();
            sc.nextLine(); //Buffer fix
            clearScreen();

            if(opt == 4) {
                break;
            }
            switch(opt) {
                case 1: insertarEnTabla(conn); break;
                case 2: actualizarEnTabla(conn); break;
                case 3: eliminarEnTabla(conn); break;
            }
        }
        
    }

    private static void eliminarEnTabla(Connection conn) {
        int opt = 0;
        while(true) {
            opt = mostrarOpcionTabla();
            if(opt == 3)
                break;
            switch (opt) {
                case 1:
                    eliminarProyecto(conn);
                    break;
                case 2:
                    eliminarIngeniero(conn);
                    break;
            }       
        }
    }

    private static void eliminarIngeniero(Connection conn) {
        int id = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingresar Codigo de Ingeniero: ");
        id = sc.nextInt();
        sc.nextLine();

        // Consulta SQL
        String sql = "DELETE FROM ingeniero WHERE IDIng = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            
            int filasEliminadas = pstmt.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("Ingeniero eliminado con éxito.");
            } else {
                System.out.println("No se encontró ningún ingeniero con el código proporcionado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar ingeniero: " + e.getMessage());
        }
        
    }

    private static void eliminarProyecto(Connection conn) {
        int id = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingresar Codigo de Proyecto: ");
        id = sc.nextInt();
        sc.nextLine();

        // Consulta SQL
        String sql = "DELETE FROM proyecto WHERE IDProy = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            
            int filasEliminadas = pstmt.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("Proyecto eliminado con éxito.");
            } else {
                System.out.println("No se encontró ningún proyecto con el código proporcionado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar proyecto: " + e.getMessage());
        }
    }

    private static void actualizarEnTabla(Connection conn) {
        int opt = 0;
        while(true) {
            opt = mostrarOpcionTabla();
            if(opt == 3)
                break;
            switch (opt) {
                case 1:
                    actualizarProyecto(conn);
                    break;
                case 2:
                    actualizarIngeniero(conn);
                    break;
            }       
        }
    }

    private static void actualizarIngeniero(Connection conn) {
        int id;
        String especialidad;
        String cargo;
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingresar Id"); id = sc.nextInt(); sc.nextLine();
        System.out.print("Actualizar Especialidad: "); especialidad =  sc.nextLine();
        System.out.print("Actualizar Cargo: "); cargo =  sc.nextLine();

        String sql = "UPDATE ingeniero SET Especialidad = ?, Cargo = ? WHERE IDIng = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, especialidad);
            pstmt.setString(2, cargo);
            pstmt.setInt(3, id);
            
            int filasActualizadas = pstmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Ingeniero actualizado con éxito.");
            } else {
                System.out.println("No se encontró ningún ingeniero con el Id proporcionado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar ingeniero: " + e.getMessage());
        }
    }

    private static void actualizarProyecto(Connection conn) {
        int id;
        String nombre;
        String FecTerminoStr;
        int IDDpto;
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingresar Id: ");
        id = sc.nextInt(); sc.nextLine(); // Consumir el salto de línea
        
        System.out.print("Ingresar nombre: ");
        nombre = sc.nextLine();
        
        System.out.print("Ingresar Fecha Termino (AAAA-MM-DD): ");
        FecTerminoStr = sc.nextLine();
        
        System.out.print("Ingresar IDDpto: ");
        IDDpto = sc.nextInt(); sc.nextLine(); // Consumir el salto de línea
        
        // Convertir la fecha de String a java.sql.Date
        Date FecTermino = Date.valueOf(FecTerminoStr);
        
        String sql = "UPDATE proyecto SET Nombre = ?, Fec_Termino = ?, IDDpto = ? WHERE IDProy = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setDate(2, FecTermino);
            pstmt.setInt(3, IDDpto);
            pstmt.setInt(4, id);
            
            int filasActualizadas = pstmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Proyecto actualizado con éxito.");
            } else {
                System.out.println("No se encontró ningún proyecto con el Id proporcionado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar proyecto: " + e.getMessage());
        }
    }

    private static void insertarEnTabla(Connection conn) {
        int opt = 0;
        while(true) {
            opt = mostrarOpcionTabla();
            if(opt == 3)
                break;
            switch (opt) {
                case 1:
                    insertarProyecto(conn);
                    break;
                case 2:
                    insertarIngeniero(conn);
                    break;
            }       
        }
    }

    private static void insertarIngeniero(Connection conn) {
        int id;
        String especialidad;
        String cargo;
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingresar Id: "); id =  sc.nextInt(); sc.nextLine();
        System.out.print("Ingresar Especialidad: "); especialidad =  sc.nextLine();
        System.out.print("Ingresar Cargo: "); cargo =  sc.nextLine();

        // Consulta SQL
        String sql = "INSERT INTO ingeniero (IDIng, Especialidad, Cargo) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, especialidad);
            pstmt.setString(3, cargo);
            pstmt.executeUpdate();
            System.out.println("Ingeniero insertado con éxito.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void insertarProyecto(Connection conn) {
        int id;
        String nombre;
        String FecInicioStr;
        String FecTerminoStr;
        int IDDpto;
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingresar Id: "); id =  sc.nextInt(); sc.nextLine();
        System.out.print("Ingresar nombre: "); nombre =  sc.nextLine();
        System.out.print("Ingresar Fecha Inicio (AAAA-MM-DD): "); FecInicioStr =  sc.nextLine();
        System.out.print("Ingresar Fecha Termino (AAAA-MM-DD): "); FecTerminoStr =  sc.nextLine();
        System.out.print("Ingresar IDDpto: "); IDDpto =  sc.nextInt(); sc.nextLine();

        // Convertir las fechas de String a java.sql.Date
        Date FecInicio = Date.valueOf(FecInicioStr);
        Date FecTermino = Date.valueOf(FecTerminoStr);
        
        // Consulta SQL
        String sql = "INSERT INTO proyecto (IDProy, Nombre, Fec_Inicio, Fec_Termino, IDDpto) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, nombre);
            pstmt.setDate(3, FecInicio);
            pstmt.setDate(4, FecTermino);
            pstmt.setInt(5, IDDpto);
            pstmt.executeUpdate();
            System.out.println("Proyecto insertado con éxito.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int mostrarOpcionTabla() {
        int opt = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("***SELECCIONAR TABLA***");
        System.out.println("1. Proyecto");
        System.out.println("2. Ingeniero");
        System.out.println("3. <--- Atras");
        System.out.print("OPCION: ");
        opt = sc.nextInt();
        sc.nextLine(); //Buffer fix
        clearScreen();
        
        return opt;
    }

    public static void main(String[] args) throws SQLException {
        // URL de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/sd";
        String user = "root";
        String password = "";

        Conexion dbConn = new Conexion(url, user, password);
        Connection conn = dbConn.connect();

        menuPrincipal(conn);
        
        dbConn.close();
    }
}

