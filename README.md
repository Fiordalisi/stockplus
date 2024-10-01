# Stockplus
Proyecto Universidad Siglo 21

### Materia 
#### Seminario de Práctica Informática

## Diagramas

### Cargar nueva categoria
```plantuml
@startuml
actor encargado
boundary ConsolaStockplus
control CargarCategoria
entity Categoria
encargado -> ConsolaStockplus: Selecciona Categorias
activate ConsolaStockplus
ConsolaStockplus --> encargado: Muestra el menú de Categorias
encargado -> ConsolaStockplus: Selecciona Cargar Nueva Categoria
ConsolaStockplus --> encargado: Solicita datos
encargado -> ConsolaStockplus: Ingresa datos
ConsolaStockplus -> CargarCategoria: Valida datos
activate CargarCategoria
deactivate Categoria
CargarCategoria -> Categoria: Carga nueva Categoria
activate Categoria
Categoria --> CargarCategoria: Confirma 
deactivate Categoria
CargarCategoria --> ConsolaStockplus: Confirma operación
deactivate CargarCategoria
ConsolaStockplus --> encargado: Muestra mensaje de confirmación
deactivate ConsolaStockplus
@enduml
```

### Cargar nuevo producto
```plantuml
@startuml
actor encargado
boundary ConsolaStockplus
control CargarProducto
entity Producto
entity Categoría
encargado -> ConsolaStockplus: Selecciona Productos
activate ConsolaStockplus
ConsolaStockplus --> encargado: Muestra el menú de productos
encargado -> ConsolaStockplus: Selecciona Cargar Nuevo Producto
ConsolaStockplus --> encargado: Solicita datos
encargado -> ConsolaStockplus: Ingresa datos
ConsolaStockplus -> CargarProducto: Valida datos
activate CargarProducto
CargarProducto -> Categoría: Busca categoría
activate Categoría
Categoría --> CargarProducto: Devuelve información
deactivate Categoría
CargarProducto -> Producto: Carga nuevo producto
activate Producto
Producto --> CargarProducto: Confirma 
deactivate Producto
CargarProducto --> ConsolaStockplus: Confirma operación
deactivate CargarProducto
ConsolaStockplus --> encargado: Muestra mensaje de confirmación
deactivate ConsolaStockplus
@enduml
```

### Cargar nuevo proveedor
```plantuml
@startuml
actor encargado
boundary ConsolaStockplus
control CargarProveedor
entity Proveedor
entity Categoría
encargado -> ConsolaStockplus: Selecciona Proveedores
activate ConsolaStockplus
ConsolaStockplus --> encargado: Muestra el menú de Proveedores
encargado -> ConsolaStockplus: Selecciona Cargar Nuevo Proveedor
ConsolaStockplus --> encargado: Solicita datos
encargado -> ConsolaStockplus: Ingresa datos
ConsolaStockplus -> CargarProveedor: Valida datos
activate CargarProveedor
CargarProveedor -> Categoría: Busca categoría
activate Categoría
Categoría --> CargarProveedor: Devuelve información
deactivate Categoría
CargarProveedor -> Proveedor: Carga nuevo Proveedor
activate Proveedor
Proveedor --> CargarProveedor: Confirma 
deactivate Proveedor
CargarProveedor --> ConsolaStockplus: Confirma operación
deactivate CargarProveedor
ConsolaStockplus --> encargado: Muestra mensaje de confirmación
deactivate ConsolaStockplus
@enduml
```

### Registrar Venta
```plantuml
actor empleado
boundary ConsolaStockplus
control RegistrarVenta
entity Producto
entity Venta


empleado -> ConsolaStockplus: Selecciona Registrar Venta
activate ConsolaStockplus
loop n productos
ConsolaStockplus -> empleado: Solicita nombre del producto
empleado -> ConsolaStockplus: Ingresa nombre del producto
ConsolaStockplus -> RegistrarVenta: Valida existencia del producto
activate RegistrarVenta
RegistrarVenta -> Producto: Consultar producto
activate Producto
Producto --> RegistrarVenta: Devuelve Información
deactivate Producto
RegistrarVenta --> ConsolaStockplus: Confirma existencia
ConsolaStockplus -> empleado: Solicita cantidad
empleado -> ConsolaStockplus: Ingresa cantidad
ConsolaStockplus -> RegistrarVenta: Valida stock
RegistrarVenta --> ConsolaStockplus: Confirma disponibilidad
deactivate RegistrarVenta
ConsolaStockplus --> empleado: Muestra mensaje de aceptación
end

empleado -> ConsolaStockplus: Selecciona Finalizar
ConsolaStockplus --> empleado: Muestra detalle de venta
ConsolaStockplus -> empleado: Solicita correo electrónico del cliente
empleado -> ConsolaStockplus: Ingresa correo electronico
ConsolaStockplus -> RegistrarVenta: Valida correo electrónico
activate RegistrarVenta
RegistrarVenta --> ConsolaStockplus: Confirma correo electrónico
ConsolaStockplus -> empleado: Solicita confirmación de venta
empleado -> ConsolaStockplus: Confirma la venta
ConsolaStockplus -> RegistrarVenta: Actualiza información
RegistrarVenta -> Venta: Carga nueva venta
activate Venta
Venta --> RegistrarVenta: Confirma
deactivate Venta
loop n productos
RegistrarVenta -> Producto: Actualiza stock
activate Producto
Producto --> RegistrarVenta: Confirma
deactivate Producto
end 

RegistrarVenta -> RegistrarVenta: Envia correo de confirmación al cliente
RegistrarVenta --> ConsolaStockplus: Confirma operación
deactivate RegistrarVenta
ConsolaStockplus --> empleado: Muestra mensaje de confirmación


deactivate ConsolaStockplus
```

### Registrar venta - Alternativo S14
```plantuml
actor empleado
boundary ConsolaStockplus
control RegistrarVenta
entity Producto
entity Venta
entity Proveedor


empleado -> ConsolaStockplus: Selecciona Registrar Venta
activate ConsolaStockplus
loop n productos
ConsolaStockplus -> empleado: Solicita nombre del producto
empleado -> ConsolaStockplus: Ingresa nombre del producto
ConsolaStockplus -> RegistrarVenta: Valida existencia del producto
activate RegistrarVenta
RegistrarVenta -> Producto: Consultar producto
activate Producto
Producto --> RegistrarVenta: Devuelve Información
deactivate Producto
RegistrarVenta --> ConsolaStockplus: Confirma existencia
ConsolaStockplus -> empleado: Solicita cantidad
empleado -> ConsolaStockplus: Ingresa cantidad
ConsolaStockplus -> RegistrarVenta: Valida stock
RegistrarVenta --> ConsolaStockplus: Confirma disponibilidad
deactivate RegistrarVenta
ConsolaStockplus --> empleado: Muestra mensaje de aceptación
end

empleado -> ConsolaStockplus: Selecciona Finalizar
ConsolaStockplus --> empleado: Muestra detalle de venta
ConsolaStockplus -> empleado: Solicita correo electrónico del cliente
empleado -> ConsolaStockplus: Ingresa correo electronico
ConsolaStockplus -> RegistrarVenta: Valida correo electrónico
activate RegistrarVenta
RegistrarVenta --> ConsolaStockplus: Confirma correo electrónico
ConsolaStockplus -> empleado: Solicita confirmación de venta
empleado -> ConsolaStockplus: Confirma la venta
ConsolaStockplus -> RegistrarVenta: Actualiza información
RegistrarVenta -> Venta: Carga nueva venta
activate Venta
Venta --> RegistrarVenta: Confirma
deactivate Venta
loop n productos
RegistrarVenta -> Producto: Actualiza stock
activate Producto
Producto --> RegistrarVenta: Confirma
RegistrarVenta -> RegistrarVenta: Verifica limite minimo
  alt#Gold #LightGreen alcanza limite minimo

  RegistrarVenta -> Proveedor: Consultar Proveedor
  Proveedor --> RegistrarVenta: Devuelve informacion
  RegistrarVenta -> RegistrarVenta: Envia pedido al proveedor
 end
deactivate Producto
end 

RegistrarVenta -> RegistrarVenta: Envia correo de confirmación al cliente
RegistrarVenta --> ConsolaStockplus: Confirma operación
deactivate RegistrarVenta
ConsolaStockplus --> empleado: Muestra mensaje de confirmación

deactivate ConsolaStockplus
```

## Diagrama de clases
```plantuml
@startuml


class Usuario {
    +id: int
    +nombre: String
    -crear(): void
}

Usuario <|-- Administrador
Usuario <|-- Encargado
Usuario <|-- Empleado

class Administrador {
    -crearUsuario(): void
    -personalizarMensaje(): void
}

class Encargado {
    -registrarProveedor(): void
    -registrarProducto(): void
    -registrarCategoria(): void
    -listarProductos(): List<Producto>
    -listarProveedores(): List<Proveedor>
    -listarCategorias(): List<Categoria> 
    -eliminarProducto(): void
    -eliminarProveedor(): void
    -eliminarCategoria(): void
    -consultarProducto(): Producto
    -consultarProveedor(): Proveedor
    -modificarProveedor(): void
    -modificarProducto(): void
    -modificarCategoria():void
}

class Empleado {
    -registrarVenta(): void
    -listarProductos(): List<Producto>
    -consultarProducto(): Producto
}

class Categoria {
    -crear(): void
    -obtener(): Categoria
    -eliminar(): void
}

class Producto {
    -crear(): void
    -obtener(): Producto
    -eliminar(): void
}

class Proveedor {
    -crear(): void
    -obtener(): Proveedor
    -eliminar(): void
}

class Categoria {
    +id: int
    +nombre: String
    +descripcion: String
    +fechaDeCreacion: Date
    +fechaDeModificacion: Date
}

class Venta {
    -crear(): void
}

class Producto {
    +id: int
    +nombre: String
    +descripcion: String
    +unidadDeMedida: String
    +stock: int
    +precioUnitario: double
    +precio: double
    +categoria: Categoria
    +limiteMinimo: int
    +cantidadDeReposición: int
    +fechaDeCreacion: Date
    +fechaDeModificacion: Date
}

Producto "1" --> "1" Categoria

class Proveedor {
    +id: int
    +nombre: String
    +email: 
    +categoria: Categoria
    +fechaDeCreacion: Date
    +fechaDeModificacion: Date
}

Proveedor "1" --> "1" Categoria


class Venta {
    +id: int
    +fecha: Date
    +emailCliente: String
    +productos: List<Productos>
    +total: double
}

Venta "1" --> "1" Empleado
Venta "1" --> "*" Producto

@enduml
```

## Diagrama de clases de despliegue
```plantuml
@startuml

cloud "Cliente Web" {
    [Navegador Web]
}

node "Servidor de Aplicaciones" {
    [Gestión de Usuarios]
    [Gestión de Productos y Proveedores]
    [Gestión de Ventas]
}

database "Servidor de Base de Datos" {
    [Base de Datos MySQL]
}

[Navegador Web] --> [Gestión de Usuarios] : Solicita registro/inicio de sesión
[Navegador Web] --> [Gestión de Productos y Proveedores] : Consulta productos/proveedores
[Navegador Web] --> [Gestión de Ventas] : Realiza transacciones

[Gestión de Usuarios] --> [Base de Datos MySQL] : Accede a datos de usuario
[Gestión de Productos y Proveedores] --> [Base de Datos MySQL] : Accede a información de productos y proveedores
[Gestión de Ventas] --> [Base de Datos MySQL] : Guarda información de ventas

note right of [Gestión de Usuarios]
    La gestión de usuarios permite registrar nuevos usuarios y autenticar
    a los existentes. Los datos se almacenan en la base de datos para
    futuras referencias.
end note

note right of [Gestión de Ventas]
    La gestión de ventas registra cada transacción realizada por los empleados,
    accediendo a los productos disponibles y vinculando la información
    con la base de datos.
end note

@enduml
```

### Diagrama Entidad-Relacion (DER)
```plantuml
@startuml
!define primary_key(x) <b><color:#b8861b><&key></color> x</b>
!define foreign_key(x) <color:#aaaaaa><&key></color> x
!define column(x) <color:#efefef><&media-record></color> x
!define table(x) entity x << (T, white) >>


table(Usuarios) {
    primary_key(id): INT
    column(nombre): VARCHAR
    column(password): VARCHAR
    column(tipo): ENUM('Administrador', 'Encargado', 'Empleado')
}

table(Productos) {
    primary_key(id): INT
    column(nombre): VARCHAR
    column(descripcion): TEXT
    column(unidadDeMedida): VARCHAR
    column(stock): INT
    column(precioUnitario): DOUBLE
    column(precio): DOUBLE
    column(limiteMinimo): INT
    column(cantidadDeReposicion): INT
    column(fechaDeCreacion): DATE
    column(fechaDeModificacion): DATE
    foreign_key(categoria_id): INT <<FK>>
}

table(Categorias) {
    primary_key(id): INT
    column(nombre): VARCHAR
    column(descripcion): TEXT
    column(fechaDeCreacion): DATE
    column(fechaDeModificacion): DATE
}
Productos }|--|| Categorias

table(Proveedores) {
    primary_key(id): INT
    column(nombre): VARCHAR
    column(email): VARCHAR
    column(prioridad): INT
    column(fechaDeCreacion): DATE
    column(fechaDeModificacion): DATE
    foreign_key(categoria_id): INT <<FK>>
}
Proveedores }|--|| Categorias

table(Ventas) {
    primary_key(id): INT
    column(fecha): DATE
    column(emailCliente): VARCHAR
    column(total): DOUBLE
    foreign_key(usuario_id): INT <<FK>>
}
Ventas }|--|| Usuarios
Ventas }|--|{ Productos 

@enduml
```