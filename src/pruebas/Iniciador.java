package pruebas;


import conexion.Conexion;
import controladorVistaPrincipal.ControladorVentanaPrincipal;
import controladorVistaPrincipal.ControladorVentanaPrincipalSecundario;
import vista.JInternalFrameInicioSesion;
import vista.Menu_1;

public class Iniciador {

	
	 public static  ControladorVentanaPrincipal controladorPrincipal;
	 public static  ControladorVentanaPrincipalSecundario controlador;
	
	public static void main(String args[]) {
		conexion.Conexion conec = new Conexion();
		 conec.empezarConexion();
		 Menu_1 ventana;	
		 ventana = new Menu_1();
		 ventana.getFrame().setVisible(true);
		 ventana.getFrame().setLocationRelativeTo(null);
		 
		 controlador =new ControladorVentanaPrincipalSecundario(ventana); 
		 
		 ventana.internalFrameInicioSesion = new JInternalFrameInicioSesion(controlador);

		 ventana.getFrame().getContentPane().remove(4);

		 ventana.getFrame().getContentPane().add(ventana.internalFrameInicioSesion);

		 ventana.internalFrameInicioSesion.setVisible(true);
			
			
		 
		 //controladorPrincipal = new ControladorVentanaPrincipal(ventana);
		 
		// ventana.setControlador(controladorPrincipal);
		// ventana.setControl(controladorPrincipal);
		 
		 
	}
	
}
