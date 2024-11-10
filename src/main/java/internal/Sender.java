package internal;

import internal.negocio.Producto;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Sender {

    final static String username = "stockplus.s21@gmail.com";
    final static String password = "qdnh qkob rwze ltnt";

    public static Session generarSesion() {
        Properties props = new Properties();

        try (FileInputStream input = new FileInputStream("src/main/java/internal/gmail_auth.properties")) {
            props.load(input);
        } catch (IOException e) {
            System.out.println(ANSI.RED.getCode() + "No se pudo leer el archivo properties: " + e.getMessage() + ANSI.RESET.getCode());
        }

        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    /**
    * EnviarFactura
     * @param correoCliente correo electronico del cliente
     * @param productos lista de productos vendidos. Contiene la info de cada producto
     * @param cantidades mapa con la relacion entre producto y cantidad vendida.
     *
     * Este metodo se ocupa de generar la conexion con el servidor de GMAIL y
     * armar el contenido del mensaje.
    * */
    public static void EnviarFactura(String correoCliente, List<Producto> productos, HashMap<String, Integer> cantidades) {
        try {
            Message message = new MimeMessage(generarSesion());
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(correoCliente)
            );
            message.setSubject("Confirmación de compra");
            message.setHeader("Content-Type", "text/html; charset=UTF-8");

            // Todo el HTML del body del correo
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            // acá se arma la tabla en formato html
            StringBuilder htmlText = new StringBuilder("<h1>🛒 Resumen de compra 🛒</h1>");
            htmlText.append("<p>Gracias por su compra. A continuación se muestra el detalle de los productos adquiridos:</p>");
            htmlText.append("<table style='width:100%; border-collapse: collapse;'>");
            htmlText.append("<tr><th style='border: 1px solid black; padding: 8px;'>Producto</th>")
                    .append("<th style='border: 1px solid black; padding: 8px;'>Descripción</th>")
                    .append("<th style='border: 1px solid black; padding: 8px;'>Unidad de Medida</th>")
                    .append("<th style='border: 1px solid black; padding: 8px;'>Cantidad</th>")
                    .append("<th style='border: 1px solid black; padding: 8px;'>Precio Unitario</th>")
                    .append("<th style='border: 1px solid black; padding: 8px;'>Total</th></tr>");

            double totalFactura = 0;

            for (Producto producto : productos) {
                String nombreProducto = producto.getNombre();
                int cantidadVendida = cantidades.getOrDefault(nombreProducto, 0);
                double totalProducto = cantidadVendida * producto.getPrecioUnitario();
                totalFactura += totalProducto;

                htmlText.append("<tr>")
                        .append("<td style='border: 1px solid black; padding: 8px;'>").append(nombreProducto).append("</td>")
                        .append("<td style='border: 1px solid black; padding: 8px;'>").append(producto.getDescripcion()).append("</td>")
                        .append("<td style='border: 1px solid black; padding: 8px;'>").append(producto.getUnidadDeMedida()).append("</td>")
                        .append("<td style='border: 1px solid black; padding: 8px;'>").append(cantidadVendida).append("</td>")
                        .append("<td style='border: 1px solid black; padding: 8px;'>$").append(String.format("%.2f", producto.getPrecioUnitario())).append("</td>")
                        .append("<td style='border: 1px solid black; padding: 8px;'>$").append(String.format("%.2f", totalProducto)).append("</td>")
                        .append("</tr>");
            }

            htmlText.append("</table>");
            htmlText.append("<h3>Total Factura: $").append(String.format("%.2f", totalFactura)).append("</h3>");

            // para asignar el html al body del mensaje
            messageBodyPart.setContent(htmlText.toString(), "text/html; charset=UTF-8");

            // el multipart es necesario para combinar dentro del body todas las partes html
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // finalmente se tiene que asignar el multipart al mensaje que se va a enviar
            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Correo enviado exitosamente!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void NotificarProveedor(String correoProveedor, Producto producto) {
        try {
            Message message = new MimeMessage(generarSesion());
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(correoProveedor)
            );
            message.setSubject("Orden de compra");
            message.setHeader("Content-Type", "text/html; charset=UTF-8");

            // Todo el HTML del body del correo
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            // acá se arma la tabla en formato html
            StringBuilder htmlText = new StringBuilder("<h1>Notificación</h1>");
            htmlText.append("<h2 style='color: #17202a;'> &#9993; Solicitamos la reposicion de nuevas cantidades</h2>");

            // info del producto
            htmlText.append("<p style='color: #17202a;'><strong>Producto: </strong>").append(producto.getNombre()).append("</p>");
            htmlText.append("<p style='color: #17202a;'><strong>Cantidad solicitada: </strong>").append(producto.getCantidadDeReposicion()).
                    append(" ").
                    append(producto.getUnidadDeMedida()).
                    append("</p>");

            htmlText.append("<p style='margin-top: 20px;'>Saludos cordiales,<br>Administración</p>");

            // para asignar el html al body del mensaje
            messageBodyPart.setContent(htmlText.toString(), "text/html; charset=UTF-8");

            // el multipart es necesario para combinar dentro del body todas las partes html
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // finalmente se tiene que asignar el multipart al mensaje que se va a enviar
            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Se solicito al proveedor la reposicion del producto: " + producto.getNombre());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
