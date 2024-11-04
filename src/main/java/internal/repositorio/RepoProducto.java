package internal.repositorio;

import internal.ANSI;
import internal.Consola;
import internal.negocio.Producto;
import internal.usuario.Administrador;
import internal.usuario.Empleado;
import internal.usuario.Encargado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class RepoProducto {

    private List<Producto> productos;
    private Connection conn;

    public RepoProducto(Connection conn) {
        this.productos = new ArrayList<>();
        this.conn = conn;
        //inicializarProductos();
    }

    private void inicializarProductos() {
        productos.add(new Producto( "Producto A", "Descripción del producto A", "kg",
                100, 15.50, "CAT1",
                new Date(), new Date()));
        productos.add(new Producto("Producto B", "Descripción del producto B", "litro",
                50, 25.00, "CAT1",
                new Date(), new Date()));
        productos.add(new Producto("Producto C", "Descripción del producto C", "unidad",
                200, 10.00, "CAT2",
                new Date(), new Date()));
    }

    public List<Producto> obtenerTodos() {
        return productos;
    }

    public void agregar(Producto producto) {
        productos.add(producto);
    }

    public Producto buscar(String nombre) {
        try {
            Statement statement = conn.createStatement();

            String query = String.format("SELECT * FROM Productos WHERE nombre = '%s'", nombre);
            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()) {
                String desc = resultSet.getString("descripcion");
                String unidad = resultSet.getString("unidad_de_medida");
                int stock = resultSet.getInt("stock");
                double precio = resultSet.getDouble("precio_unitario");
                int limite = resultSet.getInt("limite_minimo");
                int cant = resultSet.getInt("cantidad_de_reposicion");
                int cat = resultSet.getInt("categoria_id");
                Date fechaCreacion = resultSet.getDate("fecha_de_creacion");
                Date fechaModi = resultSet.getDate("fecha_de_modificacion");

                Producto prod = new Producto(nombre, desc, unidad, stock, precio, String.format("%d", cat), fechaCreacion, fechaModi);
                prod.setLimiteMinimo(limite);
                prod.setCantidadDeReposicion(cant);
                return prod;
            } else {
                statement.close();
                resultSet.close();
                return null;
            }

        } catch (Exception e) {
            System.out.println(ANSI.RED.getCode() + "Fallo al buscar producto: " + e + ANSI.RESET.getCode());
        }

        return null;
    }

    public void modificar(Producto productoModificable, String nuevaDesc, String nuevaUnidad, double nuevoPrecio){
        productoModificable.setDescripcion(nuevaDesc);
        productoModificable.setUnidadDeMedida(nuevaUnidad);
        productoModificable.setPrecioUnitario(nuevoPrecio);
        productoModificable.actualizarFecha();
    }

    public void eliminar(Producto producto) {
        productos.remove(producto);
    }

    public List<Producto> actualizarStock(HashMap<String, Integer> vendidos) {
        List<Producto> productosVendidos = new ArrayList<>();

        String query = "UPDATE Productos SET stock = ?, fecha_de_modificacion = now() WHERE nombre = ?";

        for (String clave : vendidos.keySet()) {

            Producto prod = buscar(clave);
            productosVendidos.add(prod);

            try {
                PreparedStatement sqlStatement = conn.prepareStatement(query);
                sqlStatement.setInt(1, prod.getStock() - vendidos.get(clave));
                sqlStatement.setString(2, clave);
                sqlStatement.executeUpdate();
                sqlStatement.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /*

        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            if (vendidos.containsKey(producto.getNombre())) {
                int cantidadVendida = vendidos.get(producto.getNombre());
                producto.actualizarStock(cantidadVendida);
                productosVendidos.add(producto);
            }
        }
        return productosVendidos;*/

        return productosVendidos;
    }

}
