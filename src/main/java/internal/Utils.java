package internal;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String formatearFecha(Date fecha){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(fecha);
    }

    public static boolean emailValido(String email){
        return email.contains("@");
    }
}
