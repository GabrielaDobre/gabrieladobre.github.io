import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class AplicacionConsulta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame mimarco=new MarcoAplicacion();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mimarco.setVisible(true);		

	}
}
	 

class MarcoAplicacion extends JFrame{
	
	public MarcoAplicacion(){
		
		setTitle ("Consulta BBDD");
		
		setBounds(500,300,400,400);
		
		setLayout(new BorderLayout());
		
		JPanel menus=new JPanel();
		
		menus.setLayout(new FlowLayout());
		
		secciones=new JComboBox();
		
		secciones.setEditable(false);
		
		secciones.addItem("Todos");
		
		paises=new JComboBox();
		
		paises.setEditable(false);
		
		paises.addItem("Todos");
		
		resultado= new JTextArea(4,50);
		
		resultado.setEditable(false);
		
		add(resultado);
		
		menus.add(secciones);
		
		menus.add(paises);	
		
		add(menus, BorderLayout.NORTH);
		
		add(resultado, BorderLayout.CENTER);
		
		JButton botonConsulta=new JButton("Consulta");	
		
		botonConsulta.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				ejecutaConsulta();
				
			}
			
		});
		
		add(botonConsulta, BorderLayout.SOUTH);
		
		
		
		
		//------------CONEXIÓN CON BBDD-------------------------------------
		
		try{
				
				miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/articulos3", "root", "");
				
				Statement sentencia =miConexion.createStatement();
      
				//---CARGA JCOMBOBOX SECCIONES------
      
				String consulta="SELECT DISTINCTROW SECCIÓN FROM ARTICULOS2";
				
				ResultSet rs=sentencia.executeQuery(consulta);
				
				while(rs.next()){
					
					secciones.addItem(rs.getString(1));
					
				}
				
				rs.close();
				
				
				//---CARGA JCOMBOBOX PAISES-----
				
				consulta="SELECT DISTINCTROW PAÍS_DE_ORIGEN FROM ARTICULOS2";
				
				rs=sentencia.executeQuery(consulta);
				
				while(rs.next()){
					
					paises.addItem(rs.getString(1));
					
				}
				
				rs.close();
				
				
		}catch(Exception e){
			
		}
		
	}	
	
	private void ejecutaConsulta(){
		
		ResultSet rs=null;
		
		try{
			
			resultado.setText("");
			
			String seccion=(String)secciones.getSelectedItem();
			
			String pais=(String)paises.getSelectedItem();
			
			if(!seccion.equals("Todos") && pais.equals("Todos")){				
				
			enviaConsultaSeccion=miConexion.prepareStatement(consultaSeccion);
			
			enviaConsultaSeccion.setString(1, seccion);
			
			rs=enviaConsultaSeccion.executeQuery();
				
			}else if(seccion.equals("Todos") && !pais.equals("Todos")){				
				
				enviaConsultaPais=miConexion.prepareStatement(consultaPais);
				
				enviaConsultaPais.setString(1, pais);
				
				rs=enviaConsultaPais.executeQuery();				
				
			}else if(!seccion.equals("Todos") && !pais.equals("Todos")){
				
				enviaConsultaTodos=miConexion.prepareStatement(consultaTodos);
				
				enviaConsultaTodos.setString(1, seccion);
				
				enviaConsultaTodos.setString(2, pais);
				
				rs=enviaConsultaTodos.executeQuery();	
				
			}
			
			while(rs.next()){
				
				resultado.append(rs.getString(1));
				
				resultado.append(", ");
				
				resultado.append(rs.getString(2));
				
				resultado.append(", ");
				
				resultado.append(rs.getString(3));
				
				resultado.append(", ");
				
				resultado.append(rs.getString(4));
				
				resultado.append("\n");
				
			}
			rs.close();
			
			
		}catch(Exception e){
			
			
			
		}		
		
	}
	
		
	private Connection miConexion;

	private PreparedStatement enviaConsultaSeccion;
	
	private PreparedStatement enviaConsultaPais;
	
	private PreparedStatement enviaConsultaTodos;
	
	private final String consultaSeccion="SELECT NOMBRE_ARTÍCULO, SECCIÓN, PRECIO, PAÍS_DE_ORIGEN FROM ARTICULOS2 WHERE SECCIÓN=?";
	
	private final String consultaPais="SELECT NOMBRE_ARTÍCULO, SECCIÓN, PRECIO, PAÍS_DE_ORIGEN FROM ARTICULOS2 WHERE PAÍS_DE_ORIGEN=?";
	
	private final String consultaTodos="SELECT NOMBRE_ARTÍCULO, SECCIÓN, PRECIO, PAÍS_DE_ORIGEN FROM ARTICULOS2 WHERE SECCIÓN=? AND"
			+ " PAÍS_DE_ORIGEN=?";
	
	private JComboBox secciones;
	
	private JComboBox paises;
	
	private JTextArea resultado;	

	
}


