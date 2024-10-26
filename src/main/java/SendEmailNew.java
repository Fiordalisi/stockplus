import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SendEmailNew {



    public static void main(String[] args) throws FileNotFoundException {

        // Propiedades de configuración del servidor SMTP de Gmail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Usuario y contraseña de la cuenta de Gmail (usa la contraseña de aplicación si es necesario)
        final String username = "stockplus.s21@gmail.com";  // Cambia esto por tu correo
        final String password = "qdnh qkob rwze ltnt";      // Contraseña de aplicación

        // Autenticación
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Crear el mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("stockplus.s21@gmail.com"));   // Cambia esto por tu correo
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("te.fiordalisi@gmail.com")      // Cambia esto por el correo del destinatario
            );
            message.setSubject("Confirmación de compra");

            // Crear una parte de contenido HTML para el cuerpo del mensaje
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = "<h1>Resumen de compra</h1><p>Este es un correo con una imagen incrustada:</p>"
                    + "<img src=\"cid:image_id\">";
            messageBodyPart.setContent(htmlText, "text/html");
            messageBodyPart.addHeader("TOM", "TEST");

            // Crear una parte para la imagen
            MimeBodyPart imagePart = new MimeBodyPart();
            imagePart.attachFile("src/main/java/developer.png"); // Ruta de la imagen en tu sistema
            imagePart.setContentID("<image_id>");
            imagePart.setDisposition(MimeBodyPart.INLINE);

            // Crear un multipart para combinar las partes del mensaje
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);  // Parte del mensaje HTML
            multipart.addBodyPart(imagePart);        // Parte de la imagen

            // Asignar el contenido multipart al mensaje
            message.setContent(multipart);

            // Enviar el mensaje
            Transport.send(message);

            System.out.println("Correo enviado exitosamente!");

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    String muestraContenido(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        StringBuilder a= new StringBuilder();
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            a.append(cadena);
            System.out.println(cadena);
        }
        b.close();
        return a.toString();
    }
}
