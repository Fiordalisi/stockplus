package internal.repositorio;

import internal.negocio.Categoria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepoCategoria {

    private List<Categoria> categorias;

    public RepoCategoria() {
        this.categorias = new ArrayList<>();
        inicializarCategorias();

    }

    private void inicializarCategorias() {
        categorias.add(new Categoria("CAT1", "Desc Categoria 1", new Date(), new Date()));
        categorias.add(new Categoria("CAT2", "Desc Categoria 2", new Date(), new Date()));
    }

    public boolean existeCategoria(String nombre) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }


}
