package internal;

import internal.menu.IMenu;
import internal.menu.MenuAdmin;
import internal.menu.MenuEmpleado;
import internal.menu.MenuEncargado;
import internal.repositorio.RepoUsuario;
import internal.usuario.Administrador;
import internal.usuario.Empleado;
import internal.usuario.Encargado;
import internal.usuario.Usuario;

public class Consola {
    public static void main(String[] args)  {
        Mensajes.stockplusArt();

        Login login = new Login(new RepoUsuario());
        Usuario usuario = login.iniciarSesion();
        IMenu menu = null;


        if (usuario instanceof Administrador) {
            menu = new MenuAdmin((Administrador) usuario);
        } else if (usuario instanceof Encargado) {
            menu = new MenuEncargado((Encargado) usuario);
        } else if (usuario instanceof Empleado) {
            menu = new MenuEmpleado((Empleado) usuario);
        }

        menu.mostrar();

        System.out.println(ANSI.GREEN.getCode() + "Gracias por utilizar stockplus" + ANSI.RESET.getCode());
    }
}
