package internal.repositorio;

import internal.negocio.Producto;
import internal.negocio.Proveedor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepoProveedor {
    private List<Proveedor> proveedores;

    public RepoProveedor() {
        this.proveedores = new ArrayList<>();
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
}
