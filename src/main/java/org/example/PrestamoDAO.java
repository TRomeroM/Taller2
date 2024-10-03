package org.example;
import java.sql.*;

public class PrestamoDAO {

    public void registrarPrestamo(int libroId, int usuarioId) throws SQLException {
        String sql = "INSERT INTO Prestamo (libro_id, usuario_id, fecha_prestamo) VALUES (?, ?, CURRENT_DATE)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, libroId);
            stmt.setInt(2, usuarioId);
            stmt.executeUpdate();
        }
    }

    public void registrarDevolucion(int prestamoId) throws SQLException {
        String sql = "UPDATE Prestamo SET fecha_devolucion = CURRENT_DATE WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, prestamoId);
            stmt.executeUpdate();
        }
    }
}