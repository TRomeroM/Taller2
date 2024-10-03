package org.example;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {

            // Instancias de los DAO
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            BibliotecarioDAO bibliotecarioDAO = new BibliotecarioDAO();
            LibroDAO libroDAO = new LibroDAO();

            // Entrada de datos para crear el usuario
            System.out.println("Ingrese los datos del usuario:");
            System.out.print("Nombre: ");
            String nombreUsuario = scanner.nextLine();
            System.out.print("Apellido: ");
            String apellidoUsuario = scanner.nextLine();
            System.out.print("ID de Usuario: ");
            int idUsuario = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después del entero

            // Crear y registrar un nuevo usuario en la base de datos
            Usuario usuario1 = new Usuario( nombreUsuario, apellidoUsuario, idUsuario) ;
            usuarioDAO.addUsuario(usuario1);

            // Crear bibliotecario y registrar en la base de datos
            Bibliotecario bibliotecario = new Bibliotecario("Laura", "Gomez", 202);
            bibliotecarioDAO.addBibliotecario(bibliotecario);

            // Crear y registrar libros en la base de datos
            Libro libro1 = new Libro(1, "El Quijote", "Miguel de Cervantes", "123456789");
            Libro libro2 = new Libro(2, "Cien años de soledad", "Gabriel García Márquez", "987654321");
            Libro libro3 = new Libro(3, "Don Quijote", "Miguel de Cervantes", "567891234");
            libroDAO.addLibro(libro1);
            libroDAO.addLibro(libro2);
            libroDAO.addLibro(libro3);

            // Simular el préstamo de libros
            bibliotecario.prestarLibro(libro1, usuario1);
            bibliotecario.prestarLibro(libro2, usuario1);

            // Mostrar los datos de los usuarios y bibliotecarios desde la base de datos
            System.out.println("\nDatos de los usuarios en la base de datos:");
            List<Usuario> usuarios = usuarioDAO.getAllUsuarios();
            usuarios = new ArrayList<>();
            for (Usuario u : usuarios) {
                System.out.println("Usuario: " + u.getNombre() + " " + u.getApellido() + ", ID: " + u.getIdUsuario());
            }

            System.out.println("\nDatos de los bibliotecarios en la base de datos:");
            List<Bibliotecario> bibliotecarios = bibliotecarioDAO.getAllBibliotecarios();
            bibliotecarios = new ArrayList<>();
            for (Bibliotecario b : bibliotecarios) {
                System.out.println("Bibliotecario: " + b.getNombre() + " " + b.getApellido() + ", ID: " + b.getIdEmpleado());
            }

            // Mostrar los datos de los libros
            System.out.println("\nLibros registrados en la base de datos:");
            List<Libro> libros = libroDAO.getAllLibros();
            libros = new ArrayList<>();
            for (Libro libro : libros) {
                System.out.println("Libro: " + libro.getTitulo() + " por " + libro.getAutor() + " (ISBN: " + libro.getIsbn() + ")");
            }

            // Simular devolución de libros
            System.out.println("\nDevolviendo libro: " + libro1.getTitulo());
            bibliotecario.devolverLibro(libro1, usuario1);

        } catch (SQLException e) {
            System.err.println("Error en la base de datos: " + e.getMessage());
        } finally {

        }
    }
}