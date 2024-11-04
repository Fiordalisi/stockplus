package internal.repositorio;

import internal.ANSI;
import internal.negocio.Producto;
import internal.negocio.Proveedor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public Proveedor buscar(String nombre) {
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getNombre().equalsIgnoreCase(nombre)) {
                return proveedor;
            }
        }
        return null;
    }

    public void agregar(Proveedor proveedor){
        proveedores.add(proveedor);
    }

    public void eliminar(Proveedor proveedor){
        proveedores.remove(proveedor);
    }

    public void modificar(Proveedor proveedorModificable, String email){
        proveedorModificable.setEmail(email);
        proveedorModificable.actualizarFecha();
    }

    public Proveedor buscarPorCategoria(int CatID) {
        try {
            Statement statement = conn.createStatement();

            String query = String.format("SELECT * FROM Proveedores WHERE categoria_id = '%d'", CatID);
            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()) {
                String nombre = resultSet.getString("nombre");

                String desc = resultSet.getString("descripcion");
                String email = resultSet.getString("email");

                Proveedor proveedor = new Proveedor(nombre, String.format("%d", CatID), email, new Date(), new Date());

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
