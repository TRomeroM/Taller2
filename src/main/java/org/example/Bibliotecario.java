package org.example;

class Bibliotecario extends Persona implements Gestionable
{
    private int idEmpleado;

    // Constructor
    public Bibliotecario(String nombre, String apellido, int idEmpleado) {
        super(nombre, apellido);
        this.idEmpleado = idEmpleado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    // Implementación del método mostrarDatos()
    @Override
    public void mostrarDatos() {
        System.out.println("Bibliotecario: " + nombre + " " + apellido);
        System.out.println("ID de Empleado: " + idEmpleado);
    }

    // Sobrecarga del método prestar, acepta un usuario
    public void prestar(Usuario usuario, Libro libro) {
        if (usuario.getLibrosPrestados() < 3) {
            if (libro.prestar()) {
                usuario.incrementarLibrosPrestados();
                System.out.println("El usuario " + usuario.nombre + " ha prestado el libro '" + libro.getTitulo() + "'.");
            }
        } else {
            System.out.println("El usuario " + usuario.nombre + " ya tiene el máximo de libros prestados (3).");
        }
    }

    @Override
    public void prestarLibro(Libro libro, Usuario usuario) {

    }

    @Override
    public void devolverLibro(Libro libro, Usuario usuario) {

    }
}
