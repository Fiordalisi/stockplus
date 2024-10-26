package internal.negocio;

import internal.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Producto {

    private int id;
    private String nombre;
    private String descripcion;
    private String unidadDeMedida;
    private int stock;
    private double precioUnitario;
    private String categoria;
    private int limiteMinimo;
    private int cantidadDeReposicion;
    private Date fechaDeCreacion;
    private Date fechaDeModificacion;

    public Producto(String nombre, String descripcion, String unidadDeMedida,
                    int stock, double precioUnitario, String categoria,
                    Date fechaDeCreacion, Date fechaDeModificacion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.unidadDeMedida = unidadDeMedida;
        this.stock = stock;
        this.precioUnitario = precioUnitario;
        this.categoria = categoria;
        this.limiteMinimo = limiteMinimoDefault(stock);
        this.cantidadDeReposicion = stock;
        this.fechaDeCreacion = fechaDeCreacion;
        this.fechaDeModificacion = fechaDeModificacion;
    }

    @Override
    public String toString() {

        return String.format("Nombre: %s, Descripción: %s, Unidad: %s, " +
                        "\nStock: %d, Precio: %.2f, Categoría: %s, " +
                        "\nLímite Mínimo: %d, Cantidad de Reposición: %d, " +
                        "\nFecha de Creación: %s, Fecha de Modificación: %s",
                nombre, descripcion, unidadDeMedida, stock,
                precioUnitario, categoria,
                limiteMinimo, cantidadDeReposicion,
                Utils.formatearFecha(fechaDeCreacion),
                Utils.formatearFecha(fechaDeModificacion));
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public int getStock() {
        return stock;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void actualizarFecha() {
        fechaDeModificacion = new Date();
    }

    public int getLimiteMinimo() {
        return limiteMinimo;
    }

    public void actualizarStock(int cantidadVendida) {
        stock -= cantidadVendida;
    }

    private int limiteMinimoDefault(int stock) {
        int lm = (int) Math.round(stock * 0.1);
        if (lm == 0) {
            return stock;
        }
        return lm;
    }

}
