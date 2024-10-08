package internal;

import java.util.Arrays;
import java.util.Map;

public class Mensajes {

    public static final Map<String, String[]> CONSTANT_MAP = Map.of(
            "producto", new String[]{"Cargar nuevo producto", "Consultar producto", "Listar productos",
                    "Modificar producto", "Eliminar producto"},
            "proveedor", new String[]{"Cargar nuevo proveedor", "Consultar proveedor", "Listar proveedores",
                    "Modificar proveedor", "Eliminar proveedor"},
            "categoria", new String[]{"Cargar nueva categoria", "Listar categorias",
                    "Modificar categoria", "Eliminar categoria"},
            "titulo_menu_producto", new String[]{"<<<<<< MENU PRODUCTOS >>>>>>"},
            "titulo_menu_proveedor", new String[]{"<<<<<< MENU PROVEEDORES >>>>>>"},
            "titulo_menu_categoria", new String[]{"<<<<<< MENU CATEGORIAS >>>>>>"}
    );

    public static void stockplusArt() {
        System.out.println(ANSI.BLUE.getCode() + "░██████╗ ████████╗ ░█████╗░ ░█████╗ ░██╗░░██╗ ██████╗ ░██╗░░░░ ░██╗░░░██╗ ░██████╗");
        System.out.println(ANSI.GREEN.getCode() + "██╔════╝ ╚══██╔══╝ ██╔══██╗ ██╔══██╗ ██║░██╔╝ ██╔══██╗ ██║░░░░ ░██║░░░██║ ██╔════╝");
        System.out.println(ANSI.CYAN.getCode() + "╚█████╗░░ ░░██║░░░ ██║░░██║ ██║░░╚═╝ █████═╝ ░██████╔╝ ██║░░░░ ░██║░░░██║ ╚█████╗░");
        System.out.println(ANSI.RED.getCode() + "░╚═══██╗░ ░░██║░░░ ██║░░██║ ██║░░██╗ ██╔═██╗ ░██╔═══╝ ░██║░░░░ ░██║░░░██║ ░╚═══██╗");
        System.out.println(ANSI.YELLOW.getCode() + "██████╔╝░ ░░██║░░░ ╚█████╔╝ ╚█████╔╝ ██║░╚██╗ ██║░░░░ ░███████╗ ╚██████╔╝ ██████╔╝");
        System.out.println(ANSI.BLUE.getCode() + "╚═════╝░░ ░░╚═╝░░░ ░╚════╝░ ░╚════╝ ░╚═╝░░╚═╝ ╚═╝░░░░ ░╚══════╝ ░╚═════╝ ░╚═════╝░" + ANSI.RESET.getCode());
    }

    public static void errorFormatoInvalido() {
        System.out.printf("%sEntrada no válida. Por favor, ingrese un número.%s\n", ANSI.YELLOW.getCode(), ANSI.RESET.getCode());
    }

    public static void errorOpcionInvalida(int n1, int n2) {
        System.out.printf("%sOpción no válida. Debe ingresar un numero entre %d y %d %s\n", ANSI.YELLOW.getCode(), n1, n2, ANSI.RESET.getCode());
    }

    public static void mostrarOpciones(String entidad) {

        String titulo = CONSTANT_MAP.get(String.format("titulo_menu_%s",entidad))[0];
        System.out.printf("\n%s %s", titulo, ANSI.BLUE.getCode());
        String[] opciones = CONSTANT_MAP.get(entidad);

        for (int i = 0; i < opciones.length; i++) {
            System.out.printf("\n%d) %s", i + 1, opciones[i]);
        }

        System.out.printf("\n0) Salir %s", ANSI.RESET.getCode());
        System.out.print("\nIngrese su opción: ");
    }
}
