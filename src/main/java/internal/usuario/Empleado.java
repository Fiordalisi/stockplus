package internal.usuario;

import internal.Mensajes;
import internal.Sender;
import internal.negocio.Producto;
import internal.repositorio.RepoCategoria;
import internal.repositorio.RepoProducto;

import java.util.*;

public class Empleado extends Usuario {
    private RepoProducto repoProducto;

    public Empleado(String name, String contra, RepoProducto repoProducto) {
        super(name, contra, "EMPLEADO");
        this.repoProducto = repoProducto;
    }

    public void consultarProducto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nIngrese nombre del producto: ");
        String nombre = sc.nextLine();
        Producto producto = repoProducto.buscar(nombre);
        if (producto == null) {
            Mensajes.errorBuscarProducto(nombre, false);
            return;
        }

        System.out.println(producto);
    }

    public void listarProductos() {
        for (Producto p : this.repoProducto.obtenerTodos()) {
            System.out.println("\n" + p.toString());
        }
    }

    public void registrarVenta() {
        boolean continuar = true;
        Scanner sc = new Scanner(System.in);

        HashMap<String, Integer>  vendidos = new HashMap<>();

        do {
            System.out.println("Ingrese el nombre del producto: ");
            String nombreProducto = sc.nextLine();
            Producto producto = repoProducto.buscar(nombreProducto);
            if (producto != null) {
                System.out.println("Ingrese el cantidad: ");
                int cantidad = validarCantidad(sc.nextLine(), producto.getStock());
                if (cantidad > 0) {
                    // caso donde se vuelve a repetir la venta de un producto.
                    if (vendidos.containsKey(producto.getNombre())) {
                        int acumulado = vendidos.get(producto.getNombre());
                        // controlo que no se pase del stock.
                        if (producto.getStock() - (acumulado + cantidad) < 0) {
                            Mensajes.errorSuperaStock();
                        } else {
                            // actualizo la cantidad vendida
                            vendidos.put(producto.getNombre(), acumulado + cantidad);
                        }
                    } else {
                        // primera venta de un producto
                        vendidos.put(producto.getNombre(), cantidad);
                    }
                }
            } else {
                Mensajes.errorBuscarProducto(nombreProducto, false);
            }

            System.out.println("Si desea finalizar ingrese 0 cero " +
                    "\n(caso contrario ingrese cualquier caracter): ");
            String entrada = sc.nextLine();
            if (Objects.equals(entrada, "0")) {
                continuar = false;
            }
        } while (continuar);

        if(vendidos.isEmpty()) {
            return;
        }

        System.out.println("Ingrese el correo electronico del cliente: ");
        String correoElectronico = sc.nextLine();

        System.out.println("Si desea confirmar la compra ingrese 0 cero " +
                "\n(caso contrario ingrese cualquier caracter): ");
        String entrada = sc.nextLine();
        if (Objects.equals(entrada, "0")) {
            List<Producto> productosVendidos = repoProducto.actualizarStock(vendidos);
            Sender.EnviarFactura(correoElectronico, productosVendidos, vendidos);
            Mensajes.okVentaRegistrada();
        }

    }

    private int validarCantidad(String entrada, int stock) {
        int cantidad = 0;
        try {
            cantidad = Integer.parseInt(entrada);
        } catch (Exception e) {
            Mensajes.errorFormatoInvalido();
            return 0;
        }

        if (cantidad > stock) {
            Mensajes.errorSuperaStock();
            return 0;
        }

        if (cantidad <= 0) {
            Mensajes.errorValorInvalido();
            return 0;
        }

        return cantidad;
    }
}
