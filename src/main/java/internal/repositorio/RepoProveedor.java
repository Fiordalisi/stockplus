package internal.repositorio;

import internal.ANSI;
import internal.negocio.Producto;
import internal.negocio.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class RepoProveedor {
    private List<Proveedor> proveedores;
    private Connection conn;

    public RepoProveedor(Connection conn) {
        this.proveedores = new ArrayList<>();
        this.conn = conn;
        inicializarProveedores();
    }

    private void inicializarProveedores(){
        proveedores.add(new Proveedor("PROVEEDOR A", "CAT1", "proveedor_a@gmail.com", new Date(), new Date()));
        proveedores.add(new Proveedor("PROVEEDOR B", "CAT2", "proveedor_b@gmail.com", new Date(), new Date()));
    }

    public List<Proveedor> obtenerTodos() {
        return proveedores;
    }

    public void agregar(Proveedor proveedor){
        try {
            String query = String.format("INSERT INTO Proveedores (id, nombre, email, prioridad, \n" +
                    "fecha_de_creacion, fecha_de_modificacion, categoria_id) VALUES \n" +
                    "(?, ?, ?, 1, ?, ?, 5)");
            PreparedStatement statement = conn.prepareStatement(query);

            Random rand = new Random();
            int randomNum = rand.nextInt((10000 - 10) + 1) + 10;
            statement.setInt(1, randomNum);

            statement.setString(2, proveedor.getNombre());
            statement.setString(3, proveedor.getEmail());
            java.util.Date javaDate = new java.util.Date();
            java.sql.Date date = new java.sql.Date(javaDate.getTime());
            statement.setDate(4, date);
            statement.setDate(5, date);

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(ANSI.RED.getCode() + "Fallo al guardar proveedor: " + e + ANSI.RESET.getCode());
        }
    }

    public void eliminar(Proveedor proveedor){
        String deleteQuery = "DELETE FROM Proveedores WHERE id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery);
            System.out.println("id proveedor a elmimnar: " + proveedor.getID());
            preparedStatement.setInt(1, proveedor.getID());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(ANSI.RED.getCode() + "Fallo al eliminar proveedor: " + e + ANSI.RESET.getCode());
        }
    }

    public void modificar(Proveedor proveedorModificable, String email){
        proveedorModificable.setEmail(email);
        proveedorModificable.actualizarFecha();
    }

    public Proveedor buscarPorNombre(String nombre) {
        try {
            Statement statement = conn.createStatement();

            String query = String.format("SELECT * FROM Proveedores WHERE nombre = '%s'", nombre);
            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()) {
                int ID = resultSet.getInt("id");

                String cat = resultSet.getString("categoria_id");
                String email = resultSet.getString("email");
                Date fechaCreacion = resultSet.getDate("fecha_de_creacion");
                Date fechaModi = resultSet.getDate("fecha_de_modificacion");


                Proveedor proveedor = new Proveedor(nombre,cat, email, fechaCreacion, fechaModi);
                proveedor.setID(ID);

                return proveedor;
            } else {
                statement.close();
                resultSet.close();
                return null;
            }

        } catch (Exception e) {
            System.out.println(ANSI.RED.getCode() + "Fallo al buscar proveedor: " + e + ANSI.RESET.getCode());
        }

        return null;
    }
}
