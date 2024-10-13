package internal.repositorio;

import internal.negocio.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class RepoProveedor {
    private List<Proveedor> proveedores;

    public RepoProveedor() {
        this.proveedores = new ArrayList<>();
    }
}
