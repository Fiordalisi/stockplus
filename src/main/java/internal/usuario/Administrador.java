package internal.usuario;

import internal.ANSI;
import internal.repositorio.RepoCategoria;
import internal.repositorio.RepoProducto;
import internal.repositorio.RepoProveedor;

import java.util.Scanner;

public class Administrador extends Usuario{

    public Administrador(String name, String contra) {
        super(name, contra, "ADMIN");
    }

    public Usuario crearUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSeleccione el tipo de user que desea crear: ");
        System.out.println("1. Admin");
        System.out.println("2. Empleado");
        System.out.println("3. Encargado");
        System.out.println("0. Salir");

        int tipo = scanner.nextInt();
        System.out.println("Ingrese el nombre: ");
        String nombre = scanner.next();
        System.out.println("Ingrese una contraseña: ");
        String contra = scanner.next();
        scanner.close();
        switch (tipo) {
            case 1:
                return new Administrador(nombre, contra);
            case 2:
                return new Empleado(nombre, contra);
            case 3:
                return new Encargado(nombre, contra, new RepoProducto(),
                        new RepoProveedor(), new RepoCategoria());
            default:
                System.out.println(ANSI.YELLOW.getCode() + "Opcion invalida. Cerrando..." + ANSI.RESET.getCode());
                return null;
        }
    }

    public void modificarMensaje() {

    }
}
