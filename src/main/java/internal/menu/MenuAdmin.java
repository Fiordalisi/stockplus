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
            Mensajes.mostrarOpciones("admin", true);
            String entrada = sc.next();
            try {
                opcion = Integer.parseInt(entrada);

                if (opcion < 0 || opcion > 2) {
                    Mensajes.errorOpcionInvalida(0,2);
                } else {
                    switch (opcion) {
                        case 1:
                            administrador.crearUsuario();
                            break;
                        case 2:
                            administrador.modificarMensaje();
                            break;
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
    }
}
