CREATE TABLE Usuarios (
    id INT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    tipo VARCHAR(20) NOT NULL
);

CREATE TABLE Categorias (
    id INT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    fecha_de_creacion DATE NOT NULL,
    fecha_de_modificacion DATE NOT NULL
);

CREATE TABLE Productos (
    id INT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    unidad_de_medida VARCHAR(50),
    stock INT NOT NULL,
    precio_unitario DOUBLE NOT NULL,
    limite_minimo INT NOT NULL,
    cantidad_de_reposicion INT NOT NULL,
    fecha_de_creacion DATE NOT NULL,
    fecha_de_modificacion DATE NOT NULL,
    categoria_id INT,
    FOREIGN KEY (categoria_id) REFERENCES Categorias(id)
);

CREATE TABLE Proveedores (
    id INT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    prioridad INT NOT NULL,
    fecha_de_creacion DATE NOT NULL,
    fecha_de_modificacion DATE NOT NULL,
    categoria_id INT,
    FOREIGN KEY (categoria_id) REFERENCES Categorias(id)
);

CREATE TABLE Ventas (
    id INT PRIMARY KEY,
    fecha DATE NOT NULL,
    email_cliente VARCHAR(255),
    total DOUBLE NOT NULL,
    productos_vendidos JSON NOT NULL, -- Campo JSON para almacenar el listado de productos vendidos (contiene lo especificado en la propuesta funcional)
    usuario_id INT,
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id)
);
