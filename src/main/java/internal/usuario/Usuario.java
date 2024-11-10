package internal.usuario;

public abstract class Usuario {

    private String name;
    private String contra;
    private String tipo;
    private int ID;

    public Usuario(String name, String contra, String tipo, int ID) {
        this.name = name;
        this.contra = contra;
        this.tipo = tipo;
        this.ID = ID;
    }

    public String getTipo() {
        return tipo;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public String getContra() {
        return contra;
    }
}
