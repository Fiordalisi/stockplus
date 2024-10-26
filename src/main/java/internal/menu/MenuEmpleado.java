package internal.menu;

import internal.ANSI;
import internal.Mensajes;
import internal.usuario.Empleado;

import java.util.Objects;
import java.util.Scanner;

public class MenuEmpleado implements IMenu{

    private Empleado empleado;

    public MenuEmpleado(Empleado empleado){
        this.empleado = empleado;
    }

    @Override
    public void mostrar() {
        int opcion = -1;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\n <<<<<< MENU EMPLEADO >>>>>>");
            System.out.println(ANSI.BLUE.getCode() + "1. Productos");
            System.out.println("2. Venta");
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
                            empleado.registrarVenta();
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
            Mensajes.mostrarOpciones("producto_empleado", false);
            String entrada = sc.next();
            try {
                opcion = Integer.parseInt(entrada);

                if (opcion < 0 || opcion > 2) {
                    Mensajes.errorOpcionInvalida(0,5);
                } else {
                    switch (opcion) {
                        case 1:
                            empleado.consultarProducto();
                            break;
                        case 2:
                            empleado.listarProductos();
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
