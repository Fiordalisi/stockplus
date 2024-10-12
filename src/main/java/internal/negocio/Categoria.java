package internal.negocio;

import java.util.Date;

public class Categoria {

    private int ID;
    private String nombre;
    private String descripcion;
    private Date fechaDeCreacion;
    private Date fechaDeModificacion;

    public Categoria(String nombre, String descripcion,
              Date fechaDeCreacion, Date fechaDeModificacion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaDeCreacion = fechaDeCreacion;
        this.fechaDeModificacion = fechaDeModificacion;
    }

    @Override
    public String toString() {
        return String.format("\nNombre: %s, Descripcion: %s" +
                "\nFecha de creacion: %s, Fecha de modificación: %s",
                nombre, descripcion, fechaDeCreacion, fechaDeModificacion);
    }

    public String getNombre() {
        return nombre;
    }
}
