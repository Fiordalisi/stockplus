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
                int ID = resultSet.getInt("id");

                statement.close();
                resultSet.close();
                switch (tipo) {
                    case "administrador":
                        return new Administrador(nombreUser, password, ID);
                    case "empleado":
                        return new Empleado(nombreUser, password, ID, null, null);
                    case "encargado":
                        return new Encargado(nombreUser, password, ID, null, null, null);
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
