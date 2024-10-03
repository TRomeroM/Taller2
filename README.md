Hola, 

Comparto los datos ingresados en mysql.


CREATE TABLE Persona (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    tipo VARCHAR(50)  -- Puede ser 'usuario' o 'bibliotecario'
);

-- Creación de la tabla Usuario (hereda de Persona)
CREATE TABLE Usuario (
    id INT PRIMARY KEY,
    prestamos INT,
    FOREIGN KEY (id) REFERENCES Persona(id)
);

-- Creación de la tabla Bibliotecario (hereda de Persona)
CREATE TABLE Bibliotecario (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES Persona(id)
);

-- Creación de la tabla Libro
CREATE TABLE Libro (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255),
    autor VARCHAR(255),
    isbn VARCHAR(20),
    disponible BOOLEAN
);

-- Creación de la tabla Préstamo
CREATE TABLE Prestamo (
    id INT PRIMARY KEY AUTO_INCREMENT,
    libro_id INT,
    usuario_id INT,
    fecha_prestamo DATE,
    fecha_devolucion DATE,
    FOREIGN KEY (libro_id) REFERENCES Libro(id),
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
);
-- Inserción de registros en Persona
INSERT INTO Persona (nombre, apellido, tipo) VALUES ('Juan', 'Pérez', 'usuario');
INSERT INTO Persona (nombre, apellido, tipo) VALUES ('Laura', 'Gómez', 'bibliotecario');
INSERT INTO Persona (nombre, apellido, tipo) VALUES ('Carlos', 'Ruiz', 'usuario');
INSERT INTO Persona (nombre, apellido, tipo) VALUES ('Ana', 'Martínez', 'usuario');
INSERT INTO Persona (nombre, apellido, tipo) VALUES ('Luis', 'Sánchez', 'bibliotecario');

-- Inserción de registros en Usuario
INSERT INTO Usuario (id, prestamos) VALUES (1, 0);
INSERT INTO Usuario (id, prestamos) VALUES (3, 0);
INSERT INTO Usuario (id, prestamos) VALUES (4, 0);

-- Inserción de registros en Bibliotecario
INSERT INTO Bibliotecario (id) VALUES (2);
INSERT INTO Bibliotecario (id) VALUES (5);

-- Inserción de registros en Libro
INSERT INTO Libro (titulo, autor, isbn, disponible) VALUES ('El Quijote', 'Miguel de Cervantes', '123456789', TRUE);
INSERT INTO Libro (titulo, autor, isbn, disponible) VALUES ('Cien años de soledad', 'Gabriel García Márquez', '987654321', TRUE);
INSERT INTO Libro (titulo, autor, isbn, disponible) VALUES ('Don Quijote', 'Miguel de Cervantes', '567891234', TRUE);
INSERT INTO Libro (titulo, autor, isbn, disponible) VALUES ('La Odisea', 'Homero', '432198765', TRUE);
INSERT INTO Libro (titulo, autor, isbn, disponible) VALUES ('Moby Dick', 'Herman Melville', '765432198', TRUE);

