package internal.repositorio;

import internal.ANSI;
import internal.usuario.Administrador;
import internal.usuario.Empleado;
import internal.usuario.Encargado;
import internal.usuario.Usuario;

import java.security.spec.ECField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RepoUsuario {

    private List<Usuario> usuarios;
    private Connection conn;

    public RepoUsuario(Connection conn) {
        this.usuarios = new ArrayList<>();
        this.conn = conn;
        //inicializarUsuarios();
        //inicializarUsuariosDB();
    }

    private void inicializarUsuarios(){
        Administrador admin = new Administrador("ADMIN", "111");
        admin.setRepoUsuario(this);
        usuarios.add(admin);
        usuarios.add(new Empleado("EMPLEADO", "123456", new RepoProducto(null)));
        usuarios.add(new Encargado("ENCARGADO", "123456",
                new RepoProducto(null), new RepoProveedor(null), new RepoCategoria(null)));
    }

    private void inicializarUsuariosDB(){
        try {
            // Crea un objeto Statement
            Statement statement = conn.createStatement();

            // Realiza la consulta
            String query = "SELECT * FROM Usuarios";
            ResultSet resultSet = statement.executeQuery(query);

            // Procesa los resultados
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String password = resultSet.getString("password");
                String tipo = resultSet.getString("tipo");
                // Imprime los datos
                System.out.println("Nombre: " + nombre + ", password: " + password + " tipo: " + tipo);

                switch (tipo) {
                    case "administrador":
                        usuarios.add(new Administrador(nombre, password));
                    case "empleado":
                        usuarios.add(new Empleado(nombre, password, null));
                    case "encargado":
                        usuarios.add(new Encargado(nombre, password, null, null, null));
                }
            }

            // Cierra el ResultSet y Statement
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(ANSI.RED.getCode() + "Fallo al inicializar usuarios: " + e + ANSI.RESET.getCode());
        }
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
        try {
            Statement statement = conn.createStatement();

            String query = String.format("SELECT * FROM Usuarios WHERE nombre = '%s'", nombre);
            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()) {
                String nombreUser = resultSet.getString("nombre");
                String password = resultSet.getString("password");
                String tipo = resultSet.getString("tipo");

                statement.close();
                resultSet.close();
                switch (tipo) {
                    case "administrador":
                        return new Administrador(nombreUser, password);
                    case "empleado":
                        return new Empleado(nombreUser, password, null);
                    case "encargado":
                        return new Encargado(nombreUser, password, null, null, null);
                }
            } else {
                statement.close();
                resultSet.close();
                return null;
            }

        } catch (Exception e) {
            System.out.println(ANSI.RED.getCode() + "Fallo al buscar usuario: " + e + ANSI.RESET.getCode());
        }

        return null;
    }

    public void crear(Usuario usuario) {
        usuarios.add(usuario);
    }
}
