INSERT INTO Usuarios (id, nombre, `password`, tipo) VALUES
(1, 'Admin1', 'admin123', 'administrador'),
(2, 'Empleado1', 'empleado123', 'empleado'),
(3, 'Encargado1', 'encargado123', 'encargado');


INSERT INTO Categorias (id, nombre, descripcion, fecha_de_creacion, fecha_de_modificacion) VALUES
(1, 'CAT01', 'Descripción de la categoría CAT01', '2024-01-01', '2024-01-01'),
(2, 'CAT02', 'Descripción de la categoría CAT02', '2024-02-01', '2024-02-01'),
(3, 'CAT03', 'Descripción de la categoría CAT03', '2024-03-01', '2024-03-01');


INSERT INTO Productos (id, nombre, descripcion, unidad_de_medida, stock, precio_unitario,
limite_minimo, cantidad_de_reposicion, fecha_de_creacion, fecha_de_modificacion, categoria_id) VALUES
(1, 'PROD01', 'Descripción producto PROD01', 'lt', 50, 100.00, 10, 5, '2024-01-01', '2024-01-01', 1),
(2, 'PROD02', 'Descripción producto PROD02', 'kg', 20, 200.00, 5, 2, '2024-02-01', '2024-02-01', 2),
(3, 'PROD03', 'Descripción producto PROD03', 'kg', 100, 50.00, 20, 10, '2024-03-01', '2024-03-01', 3),
(4, 'PROD03', 'Descripción producto PROD03', 'kg', 70, 70.00, 20, 10, '2024-03-01', '2024-03-01', 1);

INSERT INTO Proveedores (id, nombre, email, prioridad,
fecha_de_creacion, fecha_de_modificacion, categoria_id) VALUES
(1, 'Proveedor CAT01', 'proveedorCAT01@example.com', 1, '2024-01-01', '2024-01-01', 1),
(2, 'Proveedor CAT02', 'proveedorCAT02@example.com', 2, '2024-02-01', '2024-02-01', 2),
(3, 'Proveedor CAT03', 'proveedorCAT03@example.com', 1, '2024-03-01', '2024-03-01', 3);

INSERT INTO Ventas (id, fecha, email_cliente, total, productos_vendidos, usuario_id) VALUES
(1, '2024-10-01', 'cliente01@example.com', 300.00, '[{"id": 1, "nombre": "PROD01", "cantidad": 2}]', 1),
(2, '2024-10-02', 'cliente02@example.com', 200.00, '[{"id": 2, "nombre": "PROD02", "cantidad": 1}]', 2);