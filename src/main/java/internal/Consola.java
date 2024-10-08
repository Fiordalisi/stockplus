package internal;

import internal.menu.IMenu;

public class Consola {
    public static void main(String[] args)  {

        Mensajes.stockplusArt();

        Login login = new Login();
        IMenu menu = login.iniciarSesion();

        menu.mostrar();
        System.out.println("Gracias por utilizar stockplus");
    }
}
