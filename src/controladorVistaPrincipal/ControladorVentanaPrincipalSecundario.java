package controladorVistaPrincipal;


import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;


import javax.swing.JOptionPane;


import Animacion.Animacion;
import controladorUsuario.UsuarioDAO;
import modeloEntidades.Usuario;
import pruebas.Iniciador;
import vista.Hash;
import vista.Menu_1;

public class ControladorVentanaPrincipalSecundario extends MouseAdapter{

	private Menu_1 vista;

	public ControladorVentanaPrincipalSecundario(Menu_1 vista) {
		super();
		this.vista = vista;

	}
	
	public void mouseReleased(MouseEvent mouse) {
		
		
		if (mouse.getComponent().getName().equals("window")) {

			int posicion = vista.internalFrameInicioSesion.getPanel().labelWindow.getX();

			
			if (posicion == 375) {
				
				Animacion.mover_izquierda(375, 0, 2, 1, vista.internalFrameInicioSesion.getPanel().labelWindow);
				Animacion.mover_izquierda(0, -374, 2, 1, vista.internalFrameInicioSesion.getPanel().getPanel_Usuario());
				// Animacion.mover_izquierda(inicio, fin, retardo, salto, componente);
			} else if (posicion == 0) {
				
				Animacion.mover_derecha(0, 375, 2, 1, vista.internalFrameInicioSesion.getPanel().labelWindow);
				Animacion.mover_derecha(-374, 0, 2, 1, vista.internalFrameInicioSesion.getPanel().getPanel_Usuario());
				// Animacion.mover_derecha(inicio, fin, retardo, salto, componente);
			}

		}
	}

	public void mousePressed(MouseEvent mouse) {

		if (mouse.getComponent().getName().equals("cerrar")) {

			

			vista.getFrame().dispose();

		} else if (mouse.getComponent().getName().equals("ingresar")) {
			System.out.println("Ingresar");

			UsuarioDAO usql = new UsuarioDAO();
			Usuario usuario = new Usuario();

			String pass = new String(vista.internalFrameInicioSesion.getPanel().passwordField.getPassword());

			if (!vista.internalFrameInicioSesion.getPanel().passwordField.getPassword().equals("") && !vista.internalFrameInicioSesion.getPanel().txtUsuario.getText().equals("")) {
				String nuevoPass = Hash.sha1(pass);
				usuario.setNombre(vista.internalFrameInicioSesion.getPanel().txtUsuario.getText());
				usuario.setContraseña(nuevoPass);
				usuario.setTipousuario(null);
				try {

					if (UsuarioDAO.Login(usuario) == true) {

						if(usuario.getTipousuario().equals("EMPLEADO")) {
							
							
							if(Iniciador.controladorPrincipal == null) {
							Iniciador.controladorPrincipal = new ControladorVentanaPrincipal(vista, "EMPLEADO");
							}else {
								Iniciador.controladorPrincipal.setRol("EMPLEADO");
							}
							
							// vista.setControl(Iniciador.controladorPrincipal);
							 
							 vista.setControlador(Iniciador.controladorPrincipal);
							 
							
							
							Iniciador.controladorPrincipal.openCuentasCaja();
							
						}else if(usuario.getTipousuario().equals("ADMINISTRADOR")) {
							
						
							if(Iniciador.controladorPrincipal == null) {
							Iniciador.controladorPrincipal = new ControladorVentanaPrincipal(vista, "ADMINISTRADOR");
							}else {
								Iniciador.controladorPrincipal.setRol("ADMINISTRADOR");
							}
							
							//vista.setControl(Iniciador.controladorPrincipal);
							 
							 vista.setControlador(Iniciador.controladorPrincipal);
							 
							 
							
							Iniciador.controladorPrincipal.openCuentasCaja();
							
						}
						
						
					} else {
						JOptionPane.showMessageDialog(null, "Datos Incorrectos");
					}

				} catch (HeadlessException | SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage() + " Error en el metodo LOGIN");
					;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Campos Incompletos");
			}

		}

	}
}
