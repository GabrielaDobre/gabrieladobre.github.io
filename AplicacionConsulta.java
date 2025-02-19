import java.sql.SQLException;
import javax.swing.*;

/**
 * Clase principal que inicia la aplicación de consulta de base de datos.
 * Crea una instancia de la interfaz gráfica y maneja posibles errores de conexión.
 * 
 * @author GabrielaDobre
 * @version 1.0
 */
public class AplicacionConsulta {

    /**
     * Método principal que ejecuta la aplicación.
     * 
     * @param args Argumentos de línea de comandos (no utilizados en esta aplicación).
     */
    public static void main(String[] args) {
        JFrame mimarco = null;
        try {
            mimarco = new MarcoAplicacionConsulta();
        } catch (SQLException e) {
            // Manejo de excepción en caso de error de conexión
            e.printStackTrace();
        }
        
        if (mimarco != null) {
            mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mimarco.setVisible(true);
        }
    }
}

