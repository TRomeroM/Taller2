package org.example;
// Interfaz Gestionable
interface Gestionable {
    void prestarLibro(Libro libro, Usuario usuario);
    void devolverLibro(Libro libro, Usuario usuario);
}