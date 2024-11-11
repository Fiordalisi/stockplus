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
import java.sql.Date;
import java.util.*;

public class RepoProducto {

    private List<Producto> productos;
    private Connection conn;

    public RepoProducto(Connection conn) {
        this.productos = new ArrayList<>();
        this.conn = conn;
    }


    public List<Producto> obtenerTodos() {
        List<Producto> todos = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();

            String query = "SELECT * FROM Productos";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
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
                prod.setId(id);
                todos.add(prod);
            }

            statement.close();
            resultSet.close();


        } catch (Exception e) {
            System.out.println(ANSI.RED.getCode() + "Fallo al buscar producto: " + e + ANSI.RESET.getCode());
        }

        return todos;
    }

    public void agregar(Producto producto) {
        try {
            String query = String.format("INSERT INTO Productos (id, nombre, descripcion, unidad_de_medida, stock, precio_unitario, \n" +
                    "limite_minimo, cantidad_de_reposicion, fecha_de_creacion, fecha_de_modificacion, categoria_id) VALUES\n" +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)");
            PreparedStatement statement = conn.prepareStatement(query);

            Random rand = new Random();
            int randomNum = rand.nextInt((10000 - 10) + 1) + 10;
            statement.setInt(1, randomNum);

            statement.setString(2, producto.getNombre());
            statement.setString(3, producto.getDescripcion());
            statement.setString(4, producto.getUnidadDeMedida());
            statement.setInt(5, producto.getStock());
            statement.setDouble(6, producto.getPrecioUnitario());
            statement.setInt(7, producto.getLimiteMinimo());
            statement.setInt(8, producto.getCantidadDeReposicion());

            java.util.Date javaDate = new java.util.Date();
            Date date = new Date(javaDate.getTime());
            statement.setDate(9, date);
            statement.setDate(10, date);

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(ANSI.RED.getCode() + "Fallo al guardar producto: " + e + ANSI.RESET.getCode());
        }
    }

    public Producto buscar(String nombre) {
        try {
            Statement statement = conn.createStatement();

            String query = String.format("SELECT * FROM Productos WHERE nombre = '%s'", nombre);
            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()) {
                int id = resultSet.getInt("id");
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
                prod.setId(id);
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

    public String mailProveedor(int IDProducto) {
        try {
            Statement statement = conn.createStatement();

            String query = String.format("SELECT email FROM Proveedores WHERE categoria_id = " +
                    "(SELECT categoria_id FROM Productos WHERE id = %d)", IDProducto);
            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()) {
                return resultSet.getString("email");
            } else {
                statement.close();
                resultSet.close();
                return "";
            }

        } catch (Exception e) {
            System.out.println(ANSI.RED.getCode() + "Fallo al buscar mail del proveedor: " + e + ANSI.RESET.getCode());
        }

        return "";
    }

    public void modificar(Producto productoModificable, String nuevaDesc, String nuevaUnidad, double nuevoPrecio){
        productoModificable.setDescripcion(nuevaDesc);
        productoModificable.setUnidadDeMedida(nuevaUnidad);
        productoModificable.setPrecioUnitario(nuevoPrecio);
        productoModificable.actualizarFecha();
    }

    public void eliminar(Producto producto) {
        String deleteQuery = "DELETE FROM Productos WHERE id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, producto.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(ANSI.RED.getCode() + "Fallo al eliminar producto: " + e + ANSI.RESET.getCode());
        }
    }

    public List<Producto> actualizarStock(HashMap<String, Integer> vendidos) {
        List<Producto> productosVendidos = new ArrayList<>();

        String query = "UPDATE Productos SET stock = ?, fecha_de_modificacion = now() WHERE nombre = ?";

        for (String clave : vendidos.keySet()) {

            Producto prod = buscar(clave);

            try {
                PreparedStatement sqlStatement = conn.prepareStatement(query);
                sqlStatement.setInt(1, prod.getStock() - vendidos.get(clave));
                sqlStatement.setString(2, clave);
                sqlStatement.executeUpdate();
                sqlStatement.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            prod.actualizarStock(vendidos.get(clave));
            productosVendidos.add(prod);
        }

        return productosVendidos;
    }

}
