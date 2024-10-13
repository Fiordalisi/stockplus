package internal.repositorio;

import internal.usuario.Administrador;
import internal.usuario.Empleado;
import internal.usuario.Encargado;
import internal.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RepoUsuario {

    private List<Usuario> usuarios;

    public RepoUsuario() {
        this.usuarios = new ArrayList<>();
        inicializarUsuarios();
    }

    private void inicializarUsuarios(){
        Administrador admin = new Administrador("ADMIN", "111");
        admin.setRepoUsuario(this);
        usuarios.add(admin);
        usuarios.add(new Empleado("EMPLEADO", "123456"));
        usuarios.add(new Encargado("ENCARGADO", "123456",
                new RepoProducto(), new RepoProveedor(), new RepoCategoria()));
    }

    public boolean existe(String nombre){
        for(Usuario usuario: usuarios){
            if(usuario.getName().equalsIgnoreCase(nombre)){
                return true;
            }
        }
        return false;
    }

    public Usuario buscar(String nombre){
        for(Usuario usuario: usuarios){
            if(usuario.getName().equalsIgnoreCase(nombre)){
                return usuario;
            }
        }
        return null;
    }

    public void crear(Usuario usuario) {
        usuarios.add(usuario);
    }
}
