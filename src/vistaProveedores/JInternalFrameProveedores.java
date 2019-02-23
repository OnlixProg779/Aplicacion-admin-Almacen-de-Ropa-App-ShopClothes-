package vistaProveedores;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import controladorVistaPrincipal.ControladorVentanaProveedores;

import java.awt.BorderLayout;

public class JInternalFrameProveedores extends JInternalFrame {
	public Panel_Proveedores panel;

	public JInternalFrameProveedores(ControladorVentanaProveedores controlador) {
		setBounds(100, 100, 708, 448);
		setTitle("PROVEEDORES");
		panel = new Panel_Proveedores();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setControlador(controlador);
		
	}

}
