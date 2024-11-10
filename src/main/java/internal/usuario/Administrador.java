package internal.usuario;

import internal.ANSI;
import internal.Mensajes;
import internal.repositorio.*;

import java.util.Scanner;

public class Administrador extends Usuario {

    private RepoUsuario repoUsuario;

    public Administrador(String name, String contra, int ID) {
        super(name, contra, "ADMIN", ID);
    }

    public void setRepoUsuario(RepoUsuario repoUsuario){
        this.repoUsuario = repoUsuario;
    }

    public Usuario crearUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSeleccione el tipo de user que desea crear: ");
        System.out.println("1. Empleado");
        System.out.println("2. Encargado");
        System.out.println("0. Salir");

        int opcion = -1;
        do {
            try {
                opcion = Integer.parseInt(scanner.next());
                if (opcion < 0 || opcion > 2) {
                    Mensajes.errorOpcionInvalida(0, 2);
                }
            } catch (NumberFormatException e) {
                Mensajes.errorFormatoInvalido();
            }
        } while (opcion < 0 || opcion > 2);


        String nombre;
        do {
            System.out.println("Ingrese el nombre: ");
            nombre = scanner.next();
            if(repoUsuario.existe(nombre)) {
                Mensajes.errorUsuarioExistente(nombre, true);
            }
        } while (repoUsuario.existe(nombre));

        System.out.println("Ingrese una contraseña: ");
        String contra = scanner.next();

        switch (opcion) {
            case 1:
                repoUsuario.crear(new Empleado(nombre, contra, 1, new RepoProducto(null), new RepoVenta(null)));
                break;
            case 2:
                repoUsuario.crear(new Encargado(nombre, contra, 1, new RepoProducto(null),
                        new RepoProveedor(null), new RepoCategoria(null)));
                break;
        }

        return null;
    }

    public void modificarMensaje() {

    }
}
