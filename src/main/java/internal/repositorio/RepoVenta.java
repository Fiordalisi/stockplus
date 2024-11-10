package internal.repositorio;

import internal.ANSI;
import internal.negocio.Producto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Random;

public class RepoVenta {

    private Connection conn;

    public RepoVenta(Connection conn) {
        this.conn = conn;
    }

    class ProductoVenta {
        private String nombre;
        private int cantidad;
        private double precio;

        public ProductoVenta(String nombre, int cantidad, double precio) {
            this.nombre = nombre;
            this.cantidad = cantidad;
            this.precio = precio;
        }
    }

    public void cargar(String email, double total, int usuarioID, List<Producto> productos) {
        try {
            String query = String.format("INSERT INTO Ventas (id, fecha, email_cliente, total, productos_vendidos, usuario_id) VALUES \n" +
                    "(?, ?, ?, ?, ?, ?)");
            PreparedStatement statement = conn.prepareStatement(query);

            Random rand = new Random();
            int randomNum = rand.nextInt((10000 - 10) + 1) + 10;
            statement.setInt(1, randomNum);

            java.util.Date javaDate = new java.util.Date();
            Date date = new Date(javaDate.getTime());
            statement.setDate(2, date);
            statement.setString(3, email);
            statement.setDouble(4, total);
            statement.setInt(6, usuarioID);

            StringBuilder productosJson = new StringBuilder("[");
            for (int i = 0; i < productos.size(); i++) {
                Producto producto = productos.get(i);

                // genero el json para cada producto
                productosJson.append("{")
                        .append("\"nombre\":\"").append(producto.getNombre()).append("\", ")
                        .append("\"precio\":").append(producto.getPrecioUnitario())
                        .append("}");

                if (i < productos.size() - 1) {
                    productosJson.append(", ");
                }
            }
            productosJson.append("]");

            statement.setString(5, productosJson.toString());

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.out.println(ANSI.RED.getCode() + "Fallo al guardar venta: " + e + ANSI.RESET.getCode());
        }
    }




}
