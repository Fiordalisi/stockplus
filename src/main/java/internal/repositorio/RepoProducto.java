package internal.repositorio;

import internal.negocio.Producto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepoProducto {

    private List<Producto> productos;

    public RepoProducto() {
        this.productos = new ArrayList<>();
        inicializarProductos();
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
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
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

}
