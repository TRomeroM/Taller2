package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.List;

public class BibliotecarioDAO {

    // Método para registrar un nuevo bibliotecario en la base de datos
    public void addBibliotecario(Bibliotecario bibliotecario) throws SQLException {
        String sqlPersona = "INSERT INTO Persona (nombre, apellido, tipo) VALUES (?, ?, 'bibliotecario')";
        String sqlBibliotecario = "INSERT INTO Bibliotecario (id) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Insertar en la tabla Persona
            try (PreparedStatement stmtPersona = conn.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS)) {
                stmtPersona.setString(1, bibliotecario.getNombre());
                stmtPersona.setString(2, bibliotecario.getApellido());
                stmtPersona.executeUpdate();

                // Obtener el ID generado para la Persona
                ResultSet rs = stmtPersona.getGeneratedKeys();
                if (rs.next()) {
                    int idPersona = rs.getInt(1);

                    // Insertar en la tabla Bibliotecario usando el ID de Persona
                    try (PreparedStatement stmtBibliotecario = conn.prepareStatement(sqlBibliotecario)) {
                        stmtBibliotecario.setInt(1, idPersona);
                        stmtBibliotecario.executeUpdate();
                    }
                }
            }
        }
    }

    // Obtener un bibliotecario por ID
    public Bibliotecario getById(int id) throws SQLException {
        String sql = "SELECT * FROM Persona p JOIN Bibliotecario b ON p.id = b.id WHERE p.id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Bibliotecario(
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("id")
                );
            }
        }
        return null;
    }

    // Actualizar un bibliotecario
    public void updateBibliotecario(Bibliotecario bibliotecario) throws SQLException {
        String sqlPersona = "UPDATE Persona SET nombre = ?, apellido = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Actualizar datos en Persona
            try (PreparedStatement stmtPersona = conn.prepareStatement(sqlPersona)) {
                stmtPersona.setString(1, bibliotecario.getNombre());
                stmtPersona.setString(2, bibliotecario.getApellido());
                stmtPersona.setInt(3, bibliotecario.getIdEmpleado());
                stmtPersona.executeUpdate();
            }
        }
    }

    // Eliminar un bibliotecario por ID
    public void deleteBibliotecario(int id) throws SQLException {
        String sqlBibliotecario = "DELETE FROM Bibliotecario WHERE id = ?";
        String sqlPersona = "DELETE FROM Persona WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Eliminar de la tabla Bibliotecario
            try (PreparedStatement stmtBibliotecario = conn.prepareStatement(sqlBibliotecario)) {
                stmtBibliotecario.setInt(1, id);
                stmtBibliotecario.executeUpdate();
            }

            // Eliminar de la tabla Persona
            try (PreparedStatement stmtPersona = conn.prepareStatement(sqlPersona)) {
                stmtPersona.setInt(1, id);
                stmtPersona.executeUpdate();
            }
        }
    }

    public List<Bibliotecario> getAllBibliotecarios() {
        return null;
    }
}