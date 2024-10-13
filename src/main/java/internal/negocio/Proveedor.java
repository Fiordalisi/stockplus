package internal.negocio;

import java.util.Date;

public class Proveedor {
    private int ID;
    private String nombre;
    private String categoria;
    private String email;
    private Date fechaDeCreacion;
    private Date fechaDeModificacion;

    public Proveedor(String nombre, String categoria, String email, Date fechaDeCreacion, Date fechaDeModificacion) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.email = email;
        this.fechaDeCreacion = fechaDeCreacion;
        this.fechaDeModificacion = fechaDeModificacion;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", email='" + email + '\'' +
                ", fechaDeCreacion=" + fechaDeCreacion +
                ", fechaDeModificacion=" + fechaDeModificacion +
                '}';
    }
}
