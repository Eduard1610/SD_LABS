package example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaccion {

    public int getID(Connection connection, String numTel) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
    
        try {
            String sql = "SELECT id FROM Usuario WHERE Numero = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, numTel);
            rs = stmt.executeQuery();
    
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
    
        return id;
    }

    public double verSaldo(Connection connection, int idUsuario) throws SQLException {
        double saldo = 0.0;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        try {
            String sql = "SELECT Monto FROM Usuario WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            rs = stmt.executeQuery();
    
            if (rs.next()) {
                saldo = rs.getDouble("Monto");
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
    
        return saldo;
    }

    public void depositar(Connection connection, double monto, int idUsuario) throws SQLException {
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

    public void retirar(Connection connection, int idUsuario, double monto) throws SQLException {
        PreparedStatement stmtRetiro = null;
        PreparedStatement stmtActualizarMonto = null;

        try {
            connection.setAutoCommit(false);

            String sqlRetiro = "INSERT INTO RegistroRetiro (IdUsuario, Monto) VALUES (?, ?)";
            stmtRetiro = connection.prepareStatement(sqlRetiro);
            stmtRetiro.setInt(1, idUsuario);
            stmtRetiro.setDouble(2, monto);
            stmtRetiro.executeUpdate();

            String sqlActualizarMonto = "UPDATE Usuario SET Monto = Monto - ? WHERE id = ?";
            stmtActualizarMonto = connection.prepareStatement(sqlActualizarMonto);
            stmtActualizarMonto.setDouble(1, monto);
            stmtActualizarMonto.setInt(2, idUsuario);
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

    public void transferir(Connection connection, long idUsuarioOrigen, String numero, double monto) throws SQLException {
        PreparedStatement stmtTransferencia = null;
        PreparedStatement stmtActualizarMontoOrigen = null;
        PreparedStatement stmtActualizarMontoDestino = null;

        int idUsuarioDestino = getID(connection, numero);

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

    public int verificarClave (Connection connection, String clave) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
    
        try {
            String sql = "SELECT id FROM Usuario WHERE ClaveSeguridad = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, clave);
            rs = stmt.executeQuery();
    
            if (rs.next()) {
                id = rs.getInt("id");
            } else {
                id = -1;
            }
        }
        catch (SQLException ex) {
            throw ex;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
    
        return id;
    }
}
