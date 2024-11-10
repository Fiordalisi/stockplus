package internal;

import internal.repositorio.*;
import internal.usuario.Administrador;
import internal.usuario.Empleado;
import internal.usuario.Encargado;
import internal.usuario.Usuario;

import java.sql.Connection;
import java.util.Objects;
import java.util.Scanner;

public class Login {

    private RepoUsuario repoUsuario;
    private Connection conn;

    public Login(RepoUsuario repoUsuario, Connection conn) {
        this.repoUsuario = repoUsuario;
        this.conn = conn;
    }

    public Usuario iniciarSesion() {
        Scanner scanner = new Scanner(System.in);
        String nombre, contra;
        Usuario usuario;

        do {
            System.out.println("Ingrese nombre: ");
            nombre = scanner.next();
            usuario = repoUsuario.buscar(nombre);
            if(usuario == null) {
                Mensajes.errorUsuarioExistente(nombre, false);
            }
        } while (usuario == null);

        do {
            System.out.println("\nIngrese contraseña: ");
            contra = scanner.next();
            if(!Objects.equals(usuario.getContra(), contra)){
                Mensajes.errorContraIncorrecta();
            }
        } while (!Objects.equals(usuario.getContra(), contra));

        int ID = usuario.getID();
        switch (usuario.getTipo()) {
            case "ADMIN":
                Administrador admin = new Administrador(nombre, contra, ID);
                return admin;
            case "EMPLEADO":
               return new Empleado(nombre, contra, ID, new RepoProducto(conn), new RepoVenta(conn));
            default:
               return new Encargado(nombre, contra, ID,
                       new RepoProducto(conn), new RepoProveedor(conn), new RepoCategoria(conn));
        }
    }


}
