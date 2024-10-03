package org.example;
// Clase Libro
public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponible;

    // Constructor
    public Libro(int id, String titulo, String autor, String isbn) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponible = true; // Por defecto el libro está disponible
    }

    // Métodos getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Método para prestar un libro
    public boolean prestar() {
        if (disponible) {
            disponible = false;
            System.out.println("El libro '" + titulo + "' ha sido prestado.");
            return true;
        } else {
            System.out.println("El libro '" + titulo + "' no está disponible.");
            return false;
        }
    }

    // Método para devolver un libro
    public void devolver() {
        if (!disponible) {
            disponible = true;
            System.out.println("El libro '" + titulo + "' ha sido devuelto.");
        } else {
            System.out.println("El libro '" + titulo + "' ya estaba disponible.");
        }
    }
}