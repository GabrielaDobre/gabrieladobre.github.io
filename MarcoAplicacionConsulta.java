import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

/**
 * Clase que representa la interfaz gráfica para consultar una base de datos.
 * Permite seleccionar secciones y países para filtrar los resultados de la consulta.
 * 
 * @author GabrielaDobre
 * @version 1.0
 */
public class MarcoAplicacionConsulta extends JFrame {
	
	 // Variables de conexión y consulta a la base de datos
    private Connection miConexion;
    private PreparedStatement enviaConsultaSeccion;
    private PreparedStatement enviaConsultaPais;
    private PreparedStatement enviaConsultaTodos;

    private final String consultaSeccion = "SELECT NOMBRE_ARTÍCULO, SECCIÓN, PRECIO, PAÍS_DE_ORIGEN FROM ARTICULOS2 WHERE SECCIÓN=?";
    private final String consultaPais = "SELECT NOMBRE_ARTÍCULO, SECCIÓN, PRECIO, PAÍS_DE_ORIGEN FROM ARTICULOS2 WHERE PAÍS_DE_ORIGEN=?";
    private final String consultaTodos = "SELECT NOMBRE_ARTÍCULO, SECCIÓN, PRECIO, PAÍS_DE_ORIGEN FROM ARTICULOS2 WHERE SECCIÓN=? AND PAÍS_DE_ORIGEN=?";
    
    // Componentes gráficos
    private JComboBox<String> secciones;
    private JComboBox<String> paises;
    private JTextArea resultado;

    /**
     * Constructor de la clase. Configura la interfaz gráfica y establece la conexión con la base de datos.
     * 
     * @throws SQLException Si hay un error al conectar con la base de datos.
     */
    public MarcoAplicacionConsulta() throws SQLException {
        setTitle("Consulta BBDD");
        setBounds(500, 300, 400, 400);
        setLayout(new BorderLayout());

        JPanel menus = new JPanel();
        menus.setLayout(new FlowLayout());

        secciones = new JComboBox<>();
        secciones.setEditable(false);
        secciones.addItem("Todos");

        paises = new JComboBox<>();
        paises.setEditable(false);
        paises.addItem("Todos");

        resultado = new JTextArea(4, 50);
        resultado.setEditable(false);

        add(resultado);
        menus.add(secciones);
        menus.add(paises);
        add(menus, BorderLayout.NORTH);
        add(resultado, BorderLayout.CENTER);

        JButton botonConsulta = new JButton("Consulta");
        botonConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ejecutaConsulta();
            }
        });

        add(botonConsulta, BorderLayout.SOUTH);

        // Conexión con la base de datos
        miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/articulos3", "root", "");
        Statement sentencia = miConexion.createStatement();

        // Cargar datos en JComboBox secciones
        String consulta = "SELECT DISTINCTROW SECCIÓN FROM ARTICULOS2";
        ResultSet rs = sentencia.executeQuery(consulta);
        while (rs.next()) {
            secciones.addItem(rs.getString(1));
        }
        rs.close();

        // Cargar datos en JComboBox países
        consulta = "SELECT DISTINCTROW PAÍS_DE_ORIGEN FROM ARTICULOS2";
        rs = sentencia.executeQuery(consulta);
        while (rs.next()) {
            paises.addItem(rs.getString(1));
        }
        rs.close();
    }

    /**
     * Ejecuta la consulta en la base de datos en función de la selección de filtros (sección y país).
     * 
     * @throws SQLException Si hay un error al ejecutar la consulta.
     */
    private void ejecutaConsulta() {
        ResultSet rs = null;
        try {
            resultado.setText("");
            String seccion = (String) secciones.getSelectedItem();
            String pais = (String) paises.getSelectedItem();

            if (!seccion.equals("Todos") && pais.equals("Todos")) {
                enviaConsultaSeccion = miConexion.prepareStatement(consultaSeccion);
                enviaConsultaSeccion.setString(1, seccion);
                rs = enviaConsultaSeccion.executeQuery();
                
            } else if (seccion.equals("Todos") && !pais.equals("Todos")) {
                enviaConsultaPais = miConexion.prepareStatement(consultaPais);
                enviaConsultaPais.setString(1, pais);
                rs = enviaConsultaPais.executeQuery();
                
            } else if (!seccion.equals("Todos") && !pais.equals("Todos")) {
                enviaConsultaTodos = miConexion.prepareStatement(consultaTodos);
                enviaConsultaTodos.setString(1, seccion);
                enviaConsultaTodos.setString(2, pais);
                rs = enviaConsultaTodos.executeQuery();
            }

            // Mostrar resultados en el JTextArea
            while (rs.next()) {
                resultado.append(rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4) + "\n");
            }
            
        } catch (SQLException e) {
        	System.out.println("La consulta no puede ejecutarse");
        	e.printStackTrace();
        }finally {
        	System.out.println("La aplicación va a cerrarse");
        	try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }
      }
   } 
}
