package internal.usuario;

import internal.negocio.Producto;

public class Encargado extends Usuario{

    /**
    * PRODUCTOS
    * */

    public Encargado(String name, String contra) {
        super(name, contra, "ENCARGADO");
    }

    public void cargarProducto(){

    }

    public void eliminarProducto(){

    }

    public void modificarProducto(){

    }

    public Producto consultarProducto() {
        return new Producto();
    }

    public void listarProductos() {
        return;
    }

    /**
     * PROVEEDORES
     * */

    public Producto consultarProveedor() {
        return new Producto();
    }

    public void listarProveedores() {
    }

    public void cargarProveedor(){

    }

    public void eliminarProveedor(){

    }

    public void modificarProveedor(){

    }

    /**
     * CATEGORIAS
     * */

    public void listarCategorias() {
    }

    public void cargarCategoria(){

    }

    public void eliminarCategoria(){

    }

    public void modificarCategoria(){

    }


}
