package internal.menu;

import internal.Mensajes;
import internal.usuario.Administrador;

import java.util.Scanner;

public class MenuAdmin implements IMenu{

    private Administrador administrador;

    public MenuAdmin(Administrador administrador) {
        this.administrador = administrador;
    }

    @Override
    public void mostrar() {
        int opcion = -1;
        Scanner sc = new Scanner(System.in);
        do {

            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Crear usuario");
            System.out.println("2. Personalizar mensaje para los clientes");
            System.out.println("0. Salir");
            System.out.print("Ingrese su opción: ");
            String entrada = sc.next();
            try {
                opcion = Integer.parseInt(entrada);

                if (opcion < 0 || opcion > 2) {
                    System.out.println("Opción no válida.");
                } else {
                    switch (opcion) {
                        case 1:
                            administrador.crearUsuario();
                            return;
                        case 2:
                            //todo: agregar
                        case 0:
                            System.out.println("Saliendo del programa...");
                            break;
                    }
                }

            } catch (NumberFormatException e) {
                Mensajes.errorFormatoInvalido();
            }

            System.out.println();

        } while (opcion != 0);

        sc.close();
    }
}
