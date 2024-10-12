package internal;

import internal.menu.IMenu;
import internal.menu.MenuAdmin;
import internal.menu.MenuEmpleado;
import internal.menu.MenuEncargado;
import internal.repositorio.RepoCategoria;
import internal.repositorio.RepoProducto;
import internal.repositorio.RepoProveedor;
import internal.repositorio.RepoUsuario;
import internal.usuario.Administrador;
import internal.usuario.Empleado;
import internal.usuario.Encargado;
import internal.usuario.Usuario;

import java.util.Scanner;

public class Login {

    private RepoUsuario repoUsuario;

    public Login(RepoUsuario repoUsuario) {
        this.repoUsuario = repoUsuario;
    }

    public Usuario iniciarSesion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese nombre: ");
        String nombre = scanner.next();
        switch (nombre) {
            case "admin":
                return new Administrador(nombre, "");
            case "empleado":
               return new Empleado(nombre, "");
            default:
               return new Encargado(nombre, "",
                       new RepoProducto(), new RepoProveedor(), new RepoCategoria());
        }
    }


}
