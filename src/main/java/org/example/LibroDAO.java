package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    // Método para agregar un nuevo libro a la base de datos
    public void addLibro(Libro libro) throws SQLException {
        String sql = "INSERT INTO Libro (titulo, autor, isbn, disponible) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getIsbn());
            stmt.setBoolean(4, libro.isDisponible());
            stmt.executeUpdate();

            // Obtener el ID generado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                libro.setId(rs.getInt(1));
            }
        }
    }

    // Método para obtener un libro por su ID
    public Libro getById(int id) throws SQLException {
        String sql = "SELECT * FROM Libro WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Libro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("isbn")
                );
            }
        }
        return null;
    }

    // Método para actualizar un libro en la base de datos
    public void updateLibro(Libro libro) throws SQLException {
        String sql = "UPDATE Libro SET titulo = ?, autor = ?, isbn = ?, disponible = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getIsbn());
            stmt.setBoolean(4, libro.isDisponible());
            stmt.setInt(5, libro.getId());
            stmt.executeUpdate();
        }
    }

    // Método para eliminar un libro de la base de datos por su ID
    public void deleteLibro(int id) throws SQLException {
        String sql = "DELETE FROM Libro WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método para obtener todos los libros
    public List<Libro> getAllLibros() throws SQLException {
        String sql = "SELECT * FROM Libro";
        List<Libro> libros = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                libros.add(new Libro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("isbn")
                ));
            }
        }
        return libros;
    }
}
