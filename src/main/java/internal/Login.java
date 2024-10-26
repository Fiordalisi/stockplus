package internal;

import internal.repositorio.RepoCategoria;
import internal.repositorio.RepoProducto;
import internal.repositorio.RepoProveedor;
import internal.repositorio.RepoUsuario;
import internal.usuario.Administrador;
import internal.usuario.Empleado;
import internal.usuario.Encargado;
import internal.usuario.Usuario;

import java.util.Objects;
import java.util.Scanner;

public class Login {

    private RepoUsuario repoUsuario;

    public Login(RepoUsuario repoUsuario) {
        this.repoUsuario = repoUsuario;
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

        switch (usuario.getTipo()) {
            case "ADMIN":
                Administrador admin = new Administrador(nombre, contra);
                admin.setRepoUsuario(new RepoUsuario());
                return admin;
            case "EMPLEADO":
               return new Empleado(nombre, contra, new RepoProducto());
            default:
               return new Encargado(nombre, contra,
                       new RepoProducto(), new RepoProveedor(), new RepoCategoria());
        }
    }


}
