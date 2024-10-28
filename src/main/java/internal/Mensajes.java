package internal;

import java.util.HashMap;
import java.util.Map;

public class Mensajes {

    /*public static final Map<String, String[]> OPCIONES = Map.of(
            "producto", new String[]{"Cargar nuevo producto", "Consultar producto", "Listar productos",
                    "Modificar producto", "Eliminar producto"},
            "producto_empleado", new String[]{"Consultar producto", "Listar productos"},
            "proveedor", new String[]{"Cargar nuevo proveedor", "Consultar proveedor", "Listar proveedores",
                    "Modificar proveedor", "Eliminar proveedor"},
            "categoria", new String[]{"Cargar nueva categoria", "Listar categorias",
                    "Modificar categoria", "Eliminar categoria"},
            "admin", new String[]{"Crear usuario", "Personalizar mensaje para los clientes"},
            "opciones_modificable_producto", new String[]{"Descripcion", "Unidad de medida", "Precio Unitario", "Confirmar modificaciones"},
            "titulo_menu_producto", new String[]{"<<<<<< MENU PRODUCTOS >>>>>>"},
            "titulo_menu_proveedor", new String[]{"<<<<<< MENU PROVEEDORES >>>>>>"},
            "titulo_menu_categoria", new String[]{"<<<<<< MENU CATEGORIAS >>>>>>"},
            "titulo_menu_admin", new String[]{"<<<<<< MENU ADMIN >>>>>>"}
    );*/

    public static final Map<String, String[]> OPCIONES = new HashMap<>();

    static {
        OPCIONES.put("producto", new String[]{"Cargar nuevo producto", "Consultar producto", "Listar productos",
                "Modificar producto", "Eliminar producto"});
        OPCIONES.put("producto_empleado", new String[]{"Consultar producto", "Listar productos"});
        OPCIONES.put("proveedor", new String[]{"Cargar nuevo proveedor", "Consultar proveedor", "Listar proveedores",
                "Modificar proveedor", "Eliminar proveedor"});
        OPCIONES.put("categoria", new String[]{"Cargar nueva categoria", "Listar categorias",
                "Modificar categoria", "Eliminar categoria"});
        OPCIONES.put("admin", new String[]{"Crear usuario", "Personalizar mensaje para los clientes"});
        OPCIONES.put("opciones_modificable_producto", new String[]{"Descripcion", "Unidad de medida", "Precio Unitario", "Confirmar modificaciones"});
        OPCIONES.put("titulo_menu_producto", new String[]{"<<<<<< MENU PRODUCTOS >>>>>>"});
        OPCIONES.put("titulo_menu_proveedor", new String[]{"<<<<<< MENU PROVEEDORES >>>>>>"});
        OPCIONES.put("titulo_menu_categoria", new String[]{"<<<<<< MENU CATEGORIAS >>>>>>"});
        OPCIONES.put("titulo_menu_admin", new String[]{"<<<<<< MENU ADMIN >>>>>>"});
    }

    public static void stockplusArt() {
        System.out.println(ANSI.BLUE.getCode() + "░██████╗ ████████╗ ░█████╗░ ░█████╗ ░██╗░░██╗ ██████╗ ░██╗░░░░ ░██╗░░░██╗ ░██████╗");
        System.out.println(ANSI.GREEN.getCode() + "██╔════╝ ╚══██╔══╝ ██╔══██╗ ██╔══██╗ ██║░██╔╝ ██╔══██╗ ██║░░░░ ░██║░░░██║ ██╔════╝");
        System.out.println(ANSI.CYAN.getCode() + "╚█████╗░░ ░░██║░░░ ██║░░██║ ██║░░╚═╝ █████═╝ ░██████╔╝ ██║░░░░ ░██║░░░██║ ╚█████╗░");
        System.out.println(ANSI.RED.getCode() + "░╚═══██╗░ ░░██║░░░ ██║░░██║ ██║░░██╗ ██╔═██╗ ░██╔═══╝ ░██║░░░░ ░██║░░░██║ ░╚═══██╗");
        System.out.println(ANSI.YELLOW.getCode() + "██████╔╝░ ░░██║░░░ ╚█████╔╝ ╚█████╔╝ ██║░╚██╗ ██║░░░░ ░███████╗ ╚██████╔╝ ██████╔╝");
        System.out.println(ANSI.BLUE.getCode() + "╚═════╝░░ ░░╚═╝░░░ ░╚════╝░ ░╚════╝ ░╚═╝░░╚═╝ ╚═╝░░░░ ░╚══════╝ ░╚═════╝ ░╚═════╝░" + ANSI.RESET.getCode());
    }

    public static void errorSuperaStock() {
        System.out.printf("%sLa cantidad ingresada supera el stock del producto.%s\n", ANSI.YELLOW.getCode(), ANSI.RESET.getCode());
    }

    public static void errorFormatoInvalido() {
        System.out.printf("%sEntrada no válida. Por favor, ingrese un número.%s\n", ANSI.YELLOW.getCode(), ANSI.RESET.getCode());
    }

    public static void errorFormatoDecimalInvalido() {
        System.out.printf("%sEntrada no válida. Por favor, ingrese un número decimal correcto.%s\n", ANSI.YELLOW.getCode(), ANSI.RESET.getCode());
    }

    public static void errorOpcionInvalida(int n1, int n2) {
        System.out.printf("%sOpción no válida. Debe ingresar un numero entre %d y %d %s\n", ANSI.YELLOW.getCode(), n1, n2, ANSI.RESET.getCode());
    }

    public static void errorValorInvalido() {
        System.out.printf("%sValor invalido. Debe ingresar un numero mayor a 0%s\n", ANSI.YELLOW.getCode(), ANSI.RESET.getCode());
    }

    public static void errorEntradaVacia(){
        System.out.printf("%sValor invalido. Ingrese nuevamente%s\n", ANSI.YELLOW.getCode(), ANSI.RESET.getCode());
    }

    public static void errorCategoriaExistente(String nombre) {
        System.out.printf("%sLa categoria %s no existe en el sistema. Recuerde verificar las categorias disponibles.\n%s",
                ANSI.YELLOW.getCode(), nombre, ANSI.RESET.getCode());
    }

    public static void errorBuscarProducto(String nombre, boolean existe) {
        System.out.printf("%sEl producto %s %s existe en el sistema. Recuerde verificar los productos cargados.\n%s",
                ANSI.YELLOW.getCode(), nombre, (existe? "ya":"no"), ANSI.RESET.getCode());
    }

    public static void salidaProductoNoCargado() {
        System.out.printf("%s\nNo se puedo cargar el producto. Debe intentarlo nuevamente%s\n", ANSI.YELLOW.getCode(), ANSI.RESET.getCode());
    }

    public static void errorUsuarioExistente(String nombre, boolean existe) {
        System.out.printf("%s%s existe un usuario con nombre %s registrado en el sistema.\n%s",
                ANSI.YELLOW.getCode(),  existe? "Ya": "No", nombre, ANSI.RESET.getCode());
    }

    public static void errorContraIncorrecta() {
        System.out.printf("%sContraseña incorrecta.\n%s",
                ANSI.RED.getCode(), ANSI.RESET.getCode());
    }

    public static void errorEmailInvalido() {
        System.out.printf("%sEl correo ingresado no es valido.\n%s",
                ANSI.RED.getCode(), ANSI.RESET.getCode());
    }

    public static void errorBuscarProveedor(String nombre, boolean existe) {
        System.out.printf("%sEl proveedor %s %s existe en el sistema. Recuerde verificar los proveedores cargados.\n%s",
                ANSI.YELLOW.getCode(), nombre, (existe? "ya":"no"), ANSI.RESET.getCode());
    }

    public static void mostrarOpciones(String entidad, boolean mostrarTitulo) {
        // casos donde no es necesario un titulo para motrar opciones
        if (mostrarTitulo) {
            String titulo = OPCIONES.get(String.format("titulo_menu_%s",entidad))[0];
            System.out.printf("\n%s %s", titulo, ANSI.BLUE.getCode());
        }

        String[] opciones = OPCIONES.get(entidad);
        for (int i = 0; i < opciones.length; i++) {
            System.out.printf("\n%d) %s", i + 1, opciones[i]);
        }

        System.out.printf("\n0) Volver %s", ANSI.RESET.getCode());
        System.out.print("\nIngrese su opción: ");
    }

    public static void okModificacionesGuargadas(){
        System.out.println(ANSI.GREEN.getCode() + "\nSe han guardado las modificaciones exitosamente" + ANSI.RESET.getCode());
    }

    public static void okVentaRegistrada(){
        System.out.println(ANSI.GREEN.getCode() + "\nSe ha registrado la venta exitosamente" + ANSI.RESET.getCode());
    }
}
