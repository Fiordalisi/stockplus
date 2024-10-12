package internal.usuario;

public abstract class Usuario {

    private String name;
    private String contra;
    private String tipo;

    public Usuario(String name, String contra, String tipo) {
        this.name = name;
        this.contra = contra;
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }
}
