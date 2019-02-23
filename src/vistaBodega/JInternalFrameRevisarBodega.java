package vistaBodega;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import controladorVistaBodega.ControladorVentanaBodegaRevisar;

import java.awt.BorderLayout;

public class JInternalFrameRevisarBodega extends JInternalFrame {
	private Panel_Bodega panel;


	public Panel_Bodega getPanel() {
		return panel;
	}


	/**
	 * Create the frame.
	 */
	public JInternalFrameRevisarBodega(ControladorVentanaBodegaRevisar controlador) {
		setBounds(100, 100, 450, 300);
		
		panel = new Panel_Bodega();
		getContentPane().add(panel, BorderLayout.CENTER);
		setTitle("BODEGA - Stock de productos");
		panel.setControlador(controlador);
	}

}
