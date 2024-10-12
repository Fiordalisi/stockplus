package internal.menu;

import internal.usuario.Empleado;

public class MenuEmpleado implements IMenu{

    private Empleado empleado;

    public MenuEmpleado(Empleado empleado){
        this.empleado = empleado;
    }

    @Override
    public void mostrar() {

    }
}
