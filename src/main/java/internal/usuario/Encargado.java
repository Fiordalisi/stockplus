package internal.usuario;

import internal.ANSI;
import internal.Mensajes;
import internal.Utils;
import internal.negocio.Producto;
import internal.negocio.Proveedor;
import internal.repositorio.RepoCategoria;
import internal.repositorio.RepoProducto;
import internal.repositorio.RepoProveedor;

import java.util.Date;
import java.util.Scanner;

public class Encargado extends Usuario {

    private RepoProducto repoProducto;
    private RepoProveedor repoProveedor;
    private RepoCategoria repoCategoria;

    /**
     * PRODUCTOS
     */

    public Encargado(String name, String contra, int ID,
                     RepoProducto repoProducto, RepoProveedor repoProveedor, RepoCategoria repoCategoria) {
        super(name, contra, "ENCARGADO", ID);
        this.repoProducto = repoProducto;
        this.repoCategoria = repoCategoria;
        this.repoProveedor = repoProveedor;
    }

    public void cargarProducto() {
        Scanner sc = new Scanner(System.in);
        String nombre, unidad, categoria;
        int stock;
        double precio = 0;

        do {
            System.out.println("\nIngrese nombre del producto: ");
            nombre = sc.nextLine();
            if (repoProducto.buscar(nombre) != null) {
                Mensajes.errorBuscarProducto(nombre, true);
            }
        } while (repoProducto.buscar(nombre) != null);

        do {
            System.out.println("Ingrese stock del producto: ");
            String valor = sc.next();
            try {
                stock = Integer.parseInt(valor);
                if (stock <= 0) {
                    Mensajes.errorValorInvalido();
                }
            } catch (Exception e) {
                Mensajes.errorFormatoInvalido();
                Mensajes.salidaProductoNoCargado();
                return;
            }
        } while (stock <= 0);

        sc.nextLine();

        System.out.println("[opcional] Ingrese una descripción: ");
        String desc = sc.nextLine();

        do {
            System.out.println("Ingrese unidad de medida: ");
            unidad = sc.nextLine();
            if (unidad.isBlank()) {
                Mensajes.errorEntradaVacia();
            }
        } while (unidad.isBlank());

        do {
            System.out.println("Ingrese precio unitario: ");
            try {
                precio = sc.nextDouble();
                if (precio <= 0) {
                    Mensajes.errorValorInvalido();
                }
            } catch (Exception e) {
                Mensajes.errorFormatoDecimalInvalido();
                sc.next();
            }

        } while (precio <= 0);

        sc.nextLine();

        do {
            System.out.println("Ingrese categoria: ");
            categoria = sc.nextLine();
            if (!repoCategoria.existeCategoria(categoria)) {
                Mensajes.errorCategoriaExistente(categoria);
            }
        } while (!repoCategoria.existeCategoria(categoria));

        Date fechaActual = new Date();
        Producto nuevo = new Producto(nombre, desc, unidad, stock, precio,
                categoria, fechaActual, fechaActual);

        repoProducto.agregar(nuevo);

        System.out.println(ANSI.GREEN.getCode() + "\nSe ha registrado el producto exitosamente" + ANSI.RESET.getCode());
    }

    public void eliminarProducto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nIngrese nombre del producto: ");
        String nombre = sc.nextLine();
        Producto producto = repoProducto.buscar(nombre);
        if (producto == null) {
            Mensajes.errorBuscarProducto(nombre, false);
            return;
        }

        repoProducto.eliminar(producto);
        System.out.println(ANSI.GREEN.getCode() + "\nEl producto ha sido eliminado exitosamente" + ANSI.RESET.getCode());
    }

    public void modificarProducto() {
        Scanner sc = new Scanner(System.in);
        String nombre, nuevaDesc, nuevaUnidad;
        double nuevoPrecio = 0;
        Producto producto;
        do {
            System.out.println("\nIngrese nombre del producto: ");
            nombre = sc.nextLine();
            producto = repoProducto.buscar(nombre);
            if (producto == null) {
                Mensajes.errorBuscarProducto(nombre, false);
            }
        } while (producto == null);

        nuevaDesc = producto.getDescripcion();
        nuevaUnidad = producto.getUnidadDeMedida();
        nuevoPrecio = producto.getPrecioUnitario();

        int opcion = -1;
        do {
            Mensajes.mostrarOpciones("opciones_modificable_producto", false);
            String entrada = sc.next();
            sc.nextLine();

            try {
                opcion = Integer.parseInt(entrada);

                if (opcion < 0 || opcion > 4) {
                    Mensajes.errorOpcionInvalida(0, 4);
                } else {
                    switch (opcion) {
                        case 0:
                            return;
                        case 1:
                            System.out.println("Ingrese nueva una descripción: ");
                            nuevaDesc = sc.nextLine();

                            break;
                        case 2:
                            do {
                                System.out.println("Ingrese nueva unidad de medida: ");
                                nuevaUnidad = sc.nextLine();
                                if (nuevaUnidad.isBlank()) {
                                    Mensajes.errorEntradaVacia();
                                }
                            } while (nuevaUnidad.isBlank());
                            break;
                        case 3:
                            do {
                                System.out.println("Ingrese precio unitario: ");
                                try {
                                    nuevoPrecio = sc.nextDouble();
                                    if (nuevoPrecio <= 0) {
                                        Mensajes.errorValorInvalido();
                                    }
                                } catch (Exception e) {
                                    Mensajes.errorFormatoDecimalInvalido();
                                    sc.next();
                                }

                            } while (nuevoPrecio <= 0);
                            break;
                        case 4:
                            opcion = 0;
                            break;
                    }
                }

            } catch (NumberFormatException e) {
                Mensajes.errorFormatoInvalido();
            }

        } while (opcion != 0);
        
        repoProducto.modificar(producto, nuevaDesc, nuevaUnidad, nuevoPrecio);
        Mensajes.okModificacionesGuargadas();
    }

    public void consultarProducto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nIngrese nombre del producto: ");
        String nombre = sc.nextLine();
        Producto producto = repoProducto.buscar(nombre);
        if (producto == null) {
            Mensajes.errorBuscarProducto(nombre, false);
            return;
        }

        System.out.println(producto);
    }

    public void listarProductos() {
        for (Producto p : this.repoProducto.obtenerTodos()) {
            System.out.println("\n" + p.toString());
        }
    }

    /**
     * PROVEEDORES
     */

    public void cargarProveedor() {
        Scanner sc = new Scanner(System.in);
        String nombre, categoria, email;

        do {
            System.out.println("\nIngrese nombre del proveedor: ");
            nombre = sc.nextLine();
            if (repoProveedor.buscarPorNombre(nombre) != null) {
                Mensajes.errorBuscarProveedor(nombre, true);
            }
        } while (repoProveedor.buscarPorNombre(nombre) != null);

        do {
            System.out.println("\nIngrese categoria: ");
            categoria = sc.nextLine();
            if (!repoCategoria.existeCategoria(categoria)) {
                Mensajes.errorCategoriaExistente(categoria);
            }
        } while (!repoCategoria.existeCategoria(categoria));

        do {
            System.out.println("Ingrese correo electronico: ");
            email = sc.nextLine();
            if (!Utils.emailValido(email)) {
                Mensajes.errorEmailInvalido();
            }
        } while (!Utils.emailValido(email));

        repoProveedor.agregar(new Proveedor(nombre, categoria, email, new Date(), new Date()));
        System.out.println(ANSI.GREEN.getCode() + "\nSe ha registrado el proveedor exitosamente" + ANSI.RESET.getCode());
    }

    public void consultarProveedor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nIngrese nombre del proveedor: ");
        String nombre = sc.nextLine();
        Proveedor proveedor = repoProveedor.buscarPorNombre(nombre);
        if (proveedor == null) {
            Mensajes.errorBuscarProveedor(nombre, false);
            return;
        }

        System.out.println(proveedor);
    }

    public void listarProveedores() {
        repoProveedor.obtenerTodos().forEach(proveedor -> System.out.println(proveedor.toString()));
    }

    public void eliminarProveedor() {
        Scanner sc = new Scanner(System.in);
        Proveedor proveedor;
        do {
            System.out.println("\nIngrese nombre del proveedor: ");
            String nombre = sc.nextLine();
            proveedor = repoProveedor.buscarPorNombre(nombre);
            if (proveedor == null) {
                Mensajes.errorBuscarProveedor(nombre, false);
            }
        } while (proveedor == null);

        repoProveedor.eliminar(proveedor);
        System.out.println(ANSI.GREEN.getCode() + "\nEl proveedor ha sido eliminado exitosamente" + ANSI.RESET.getCode());
    }

    public void modificarProveedor() {
        Scanner sc = new Scanner(System.in);
        Proveedor proveedor;
        String email;
        do {
            System.out.println("\nIngrese nombre del proveedor: ");
            String nombre = sc.nextLine();
            proveedor = repoProveedor.buscarPorNombre(nombre);
            if (proveedor == null) {
                Mensajes.errorBuscarProveedor(nombre, false);
            }
        } while (proveedor == null);

        do {
            System.out.println("Ingrese el nuevo correo electronico: ");
            email = sc.nextLine();
            if (!Utils.emailValido(email)) {
                Mensajes.errorEmailInvalido();
            }
        } while (!Utils.emailValido(email));

        repoProveedor.modificar(proveedor, email);

    }

    /**
     * CATEGORIAS
     */

    public void listarCategorias() {
    }

    public void cargarCategoria() {

    }

    public void eliminarCategoria() {

    }

    public void modificarCategoria() {

    }


}
