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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Consola {
    public static void main(String[] args)  {
        Mensajes.stockplusArt();
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
        }catch (Exception e) {
            System.out.println(ANSI.RED.getCode()+"No se puede establecer conexion con la DB"+ANSI.RESET.getCode());
            return;
        }

        Login login = new Login(new RepoUsuario(conn), conn);
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
