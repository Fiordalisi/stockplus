package internal;

import internal.menu.IMenu;
import internal.menu.MenuAdmin;
import internal.menu.MenuEmpleado;
import internal.menu.MenuEncargado;
import internal.usuario.Administrador;
import internal.usuario.Empleado;
import internal.usuario.Encargado;

import java.util.Scanner;

public class Login {

    public IMenu iniciarSesion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese nombre: ");
        String nombre = scanner.next();
        switch (nombre) {
            case "admin":
                return new MenuAdmin(new Administrador(nombre, ""));
            case "empleado":
                return new MenuEmpleado(new Empleado(nombre, ""));
            default:
                return new MenuEncargado(new Encargado(nombre, ""));
        }

    }


}
