package internal.negocio;

import internal.Utils;

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
        return String.format("Nombre: %s, Categoria: %s, Email: %s, FechaDeCreacion: %s, FechaDeModificacion: %s",
                nombre, categoria, email, Utils.formatearFecha(fechaDeCreacion), Utils.formatearFecha(fechaDeModificacion));
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void actualizarFecha() {
        fechaDeModificacion = new Date();
    }


}
