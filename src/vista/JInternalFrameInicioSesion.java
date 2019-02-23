package vista;

import java.awt.BorderLayout;


import javax.swing.JInternalFrame;

import controladorVistaPrincipal.ControladorVentanaPrincipalSecundario;


public class JInternalFrameInicioSesion extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4512715786308305130L;
	private PanelInicioSesion panel;


	public PanelInicioSesion getPanel() {
		return panel;
	}
	/**
	 * Create the frame.
	 */
	public JInternalFrameInicioSesion(ControladorVentanaPrincipalSecundario controlador) {
		setBounds(100, 100, 690, 476);
		setTitle("**INICIO DE SESION**");
		
		panel = new PanelInicioSesion();
		
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setControlInicio(controlador);

	}

}
