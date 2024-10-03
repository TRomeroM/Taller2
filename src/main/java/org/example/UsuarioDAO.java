package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.List;

public class UsuarioDAO {

    // Método para registrar un nuevo usuario en la base de datos
    public void addUsuario(Usuario usuario) throws SQLException {
        String sqlPersona = "INSERT INTO Persona (nombre, apellido, tipo) VALUES (?, ?, 'usuario')";
        String sqlUsuario = "INSERT INTO Usuario (id, prestamos) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Insertar en la tabla Persona
            try (PreparedStatement stmtPersona = conn.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS)) {
                stmtPersona.setString(1, usuario.getNombre());
                stmtPersona.setString(2, usuario.getApellido());
                stmtPersona.executeUpdate();

                // Obtener el ID generado para la Persona
                ResultSet rs = stmtPersona.getGeneratedKeys();
                if (rs.next()) {
                    int idPersona = rs.getInt(1);

                    // Insertar en la tabla Usuario usando el ID de Persona
                    try (PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario)) {
                        stmtUsuario.setInt(1, idPersona);
                        stmtUsuario.setInt(2, usuario.getLibrosPrestados());
                        stmtUsuario.executeUpdate();
                    }
                }
            }
        }
    }

    // Obtener un usuario por ID
    public Usuario getById(int id) throws SQLException {
        String sql = "SELECT * FROM Persona p JOIN Usuario u ON p.id = u.id WHERE p.id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("id")
                );
                usuario.setLibrosPrestados(rs.getInt("prestamos"));
                return usuario;
            }
        }
        return null;
    }

    // Actualizar un usuario
    public void updateUsuario(Usuario usuario) throws SQLException {
        String sqlPersona = "UPDATE Persona SET nombre = ?, apellido = ? WHERE id = ?";
        String sqlUsuario = "UPDATE Usuario SET prestamos = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Actualizar datos en Persona
            try (PreparedStatement stmtPersona = conn.prepareStatement(sqlPersona)) {
                stmtPersona.setString(1, usuario.getNombre());
                stmtPersona.setString(2, usuario.getApellido());
                stmtPersona.setInt(3, usuario.getIdUsuario());
                stmtPersona.executeUpdate();
            }

            // Actualizar datos en Usuario
            try (PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario)) {
                stmtUsuario.setInt(1, usuario.getLibrosPrestados());
                stmtUsuario.setInt(2, usuario.getIdUsuario());
                stmtUsuario.executeUpdate();
            }
        }
    }

    // Eliminar un usuario por ID
    public void deleteUsuario(int id) throws SQLException {
        String sqlUsuario = "DELETE FROM Usuario WHERE id = ?";
        String sqlPersona = "DELETE FROM Persona WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Eliminar de la tabla Usuario
            try (PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario)) {
                stmtUsuario.setInt(1, id);
                stmtUsuario.executeUpdate();
            }

            // Eliminar de la tabla Persona
            try (PreparedStatement stmtPersona = conn.prepareStatement(sqlPersona)) {
                stmtPersona.setInt(1, id);
                stmtPersona.executeUpdate();
            }
        }
    }

    public List<Usuario> getAllUsuarios() {
        return null;
    }
}