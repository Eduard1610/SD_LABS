package org.sd.lab09;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Yape {

    public static void main(String[] args) {
        Connection connection = Database.getConnection();
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("Seleccione una opción: ");
                System.out.println("1. Depositar");
                System.out.println("2. Retirar");
                System.out.println("3. Transferir");
                System.out.println("4. Salir");
                int opcion = scanner.nextInt();

                if (opcion == 4) {
                    break;
                }

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese ID del usuario: ");
                        long idUsuarioDeposito = scanner.nextLong();
                        System.out.print("Ingrese el monto a depositar: ");
                        double montoDeposito = scanner.nextDouble();
                        depositar(connection, idUsuarioDeposito, montoDeposito);
                        System.out.println("Depósito realizado con éxito.");
                        break;
                    case 2:
                        System.out.print("Ingrese ID del usuario: ");
                        long idUsuarioRetiro = scanner.nextLong();
                        System.out.print("Ingrese el monto a retirar: ");
                        double montoRetiro = scanner.nextDouble();
                        retirar(connection, idUsuarioRetiro, montoRetiro);
                        System.out.println("Retiro realizado con éxito.");
                        break;
                    case 3:
                        System.out.print("Ingrese ID del usuario origen: ");
                        long idUsuarioOrigen = scanner.nextLong();
                        System.out.print("Ingrese ID del usuario destino: ");
                        long idUsuarioDestino = scanner.nextLong();
                        System.out.print("Ingrese el monto a transferir: ");
                        double montoTransferencia = scanner.nextDouble();
                        transferir(connection, idUsuarioOrigen, idUsuarioDestino, montoTransferencia);
                        System.out.println("Transferencia realizada con éxito.");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
        } catch (SQLException ex) {
            System.err.println("ERROR: " + ex.getMessage());
        } finally {
            System.out.println("Cierra conexión a la base de datos");
            try {
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        scanner.close();
    }

    public static void depositar(Connection connection, long idUsuario, double monto) throws SQLException {
        PreparedStatement stmtDeposito = null;
        PreparedStatement stmtActualizarMonto = null;

        try {
            connection.setAutoCommit(false);

            String sqlDeposito = "INSERT INTO RegistroDeposito (IdUsuario, Monto) VALUES (?, ?)";
            stmtDeposito = connection.prepareStatement(sqlDeposito);
            stmtDeposito.setLong(1, idUsuario);
            stmtDeposito.setDouble(2, monto);
            stmtDeposito.executeUpdate();

            String sqlActualizarMonto = "UPDATE Usuario SET Monto = Monto + ? WHERE id = ?";
            stmtActualizarMonto = connection.prepareStatement(sqlActualizarMonto);
            stmtActualizarMonto.setDouble(1, monto);
            stmtActualizarMonto.setLong(2, idUsuario);
            stmtActualizarMonto.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            if (connection != null) {
                connection.rollback();
            }
            throw ex;
        } finally {
            if (stmtDeposito != null) stmtDeposito.close();
            if (stmtActualizarMonto != null) stmtActualizarMonto.close();
        }
    }

    public static void retirar(Connection connection, long idUsuario, double monto) throws SQLException {
        PreparedStatement stmtRetiro = null;
        PreparedStatement stmtActualizarMonto = null;

        try {
            connection.setAutoCommit(false);

            String sqlRetiro = "INSERT INTO RegistroRetiro (IdUsuario, Monto) VALUES (?, ?)";
            stmtRetiro = connection.prepareStatement(sqlRetiro);
            stmtRetiro.setLong(1, idUsuario);
            stmtRetiro.setDouble(2, monto);
            stmtRetiro.executeUpdate();

            String sqlActualizarMonto = "UPDATE Usuario SET Monto = Monto - ? WHERE id = ?";
            stmtActualizarMonto = connection.prepareStatement(sqlActualizarMonto);
            stmtActualizarMonto.setDouble(1, monto);
            stmtActualizarMonto.setLong(2, idUsuario);
            stmtActualizarMonto.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            if (connection != null) {
                connection.rollback();
            }
            throw ex;
        } finally {
            if (stmtRetiro != null) stmtRetiro.close();
            if (stmtActualizarMonto != null) stmtActualizarMonto.close();
        }
    }

    public static void transferir(Connection connection, long idUsuarioOrigen, long idUsuarioDestino, double monto) throws SQLException {
        PreparedStatement stmtTransferencia = null;
        PreparedStatement stmtActualizarMontoOrigen = null;
        PreparedStatement stmtActualizarMontoDestino = null;

        try {
            connection.setAutoCommit(false);

            String sqlTransferencia = "INSERT INTO RegistroPago (IdUsuarioOrigen, IdUsuarioDestino, Monto) VALUES (?, ?, ?)";
            stmtTransferencia = connection.prepareStatement(sqlTransferencia);
            stmtTransferencia.setLong(1, idUsuarioOrigen);
            stmtTransferencia.setLong(2, idUsuarioDestino);
            stmtTransferencia.setDouble(3, monto);
            stmtTransferencia.executeUpdate();

            String sqlActualizarMontoOrigen = "UPDATE Usuario SET Monto = Monto - ? WHERE id = ?";
            stmtActualizarMontoOrigen = connection.prepareStatement(sqlActualizarMontoOrigen);
            stmtActualizarMontoOrigen.setDouble(1, monto);
            stmtActualizarMontoOrigen.setLong(2, idUsuarioOrigen);
            stmtActualizarMontoOrigen.executeUpdate();

            String sqlActualizarMontoDestino = "UPDATE Usuario SET Monto = Monto + ? WHERE id = ?";
            stmtActualizarMontoDestino = connection.prepareStatement(sqlActualizarMontoDestino);
            stmtActualizarMontoDestino.setDouble(1, monto);
            stmtActualizarMontoDestino.setLong(2, idUsuarioDestino);
            stmtActualizarMontoDestino.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            if (connection != null) {
                connection.rollback();
            }
            throw ex;
        } finally {
            if (stmtTransferencia != null) stmtTransferencia.close();
            if (stmtActualizarMontoOrigen != null) stmtActualizarMontoOrigen.close();
            if (stmtActualizarMontoDestino != null) stmtActualizarMontoDestino.close();
        }
    }
}
