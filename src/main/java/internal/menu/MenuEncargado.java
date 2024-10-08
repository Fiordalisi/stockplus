package internal.menu;

import internal.ANSI;
import internal.Mensajes;
import internal.usuario.Encargado;

import java.util.Scanner;

public class MenuEncargado implements IMenu {

    private Encargado encargado;

    public MenuEncargado(Encargado encargado){
        this.encargado = encargado;
    }

    @Override
    public void mostrar() {
        int opcion = -1;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\n <<<<<< MENU ENCARGADO >>>>>>");
            System.out.println(ANSI.BLUE.getCode() + "1. Productos");
            System.out.println("2. Categorias");
            System.out.println("3. Proveedores");
            System.out.println("0. Salir");
            System.out.print(ANSI.RESET.getCode() + "Ingrese su opción: ");
            String entrada = sc.next();
            try {
                opcion = Integer.parseInt(entrada);

                if (opcion < 0 || opcion > 3) {
                    Mensajes.errorOpcionInvalida(0,3);
                } else {
                    switch (opcion) {
                        case 1:
                            menuProductos();
                            break;
                        case 2:
                            menuCategorias();
                            break;
                        case 3:
                            menuProveedores();
                            break;
                        case 0:
                            System.out.println("Saliendo del programa...");
                            break;
                    }
                }

            } catch (NumberFormatException e) {
                Mensajes.errorFormatoInvalido();
            }

        } while (opcion != 0);

        sc.close();
    }

    private void menuProductos() {
        int opcion = -1;
        Scanner sc = new Scanner(System.in);
        do {
            Mensajes.mostrarOpciones("producto");
            String entrada = sc.next();
            try {
                opcion = Integer.parseInt(entrada);

                if (opcion < 0 || opcion > 5) {
                    Mensajes.errorOpcionInvalida(0,5);
                } else {
                    switch (opcion) {
                        case 1:
                            encargado.cargarProducto();
                            break;
                        case 2:
                            encargado.consultarProducto();
                            break;
                        case 3:
                            encargado.listarProductos();
                            break;
                        case 4:
                            encargado.modificarProducto();
                            break;
                        case 5:
                            encargado.eliminarProducto();
                            break;
                        case 0:
                            System.out.println("Saliendo del programa...");
                            break;
                    }
                }

            } catch (NumberFormatException e) {
                Mensajes.errorFormatoInvalido();
            }

        } while (opcion != 0);
    }

    private void menuProveedores() {
        int opcion = -1;
        Scanner sc = new Scanner(System.in);
        do {
            Mensajes.mostrarOpciones("proveedor");
            String entrada = sc.next();
            try {
                opcion = Integer.parseInt(entrada);

                if (opcion < 0 || opcion > 5) {
                    Mensajes.errorOpcionInvalida(0, 5);
                } else {
                    switch (opcion) {
                        case 1:
                            encargado.cargarProveedor();
                            return;
                        case 2:
                            encargado.consultarProveedor();
                            return;
                        case 3:
                            encargado.listarProveedores();
                            return;
                        case 4:
                            encargado.modificarProveedor();
                            return;
                        case 5:
                            encargado.eliminarProveedor();
                            return;
                        case 0:
                            System.out.println("Saliendo del programa...");
                            break;
                    }
                }

            } catch (NumberFormatException e) {
                Mensajes.errorFormatoInvalido();
            }

        } while (opcion != 0);
    }

    private void menuCategorias() {
        int opcion = -1;
        Scanner sc = new Scanner(System.in);
        do {
            Mensajes.mostrarOpciones("categoria");
            String entrada = sc.next();
            try {
                opcion = Integer.parseInt(entrada);

                if (opcion < 0 || opcion > 4) {
                    Mensajes.errorOpcionInvalida(0, 4);
                } else {
                    switch (opcion) {
                        case 1:
                            encargado.cargarCategoria();
                            break;
                        case 2:
                            encargado.listarCategorias();
                            break;
                        case 3:
                            encargado.modificarCategoria();
                            break;
                        case 4:
                            encargado.eliminarProveedor();
                            break;
                        case 0:
                            System.out.println("Saliendo del programa...");
                            break;
                    }
                }

            } catch (NumberFormatException e) {
                Mensajes.errorFormatoInvalido();
            }

        } while (opcion != 0);
    }
}
