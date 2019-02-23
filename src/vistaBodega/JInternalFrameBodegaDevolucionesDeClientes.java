package vistaBodega;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import controladorVistaBodega.ControladorVentanaDevolucionesFacturadas;

import java.awt.BorderLayout;

public class JInternalFrameBodegaDevolucionesDeClientes extends JInternalFrame {

	private PanelDevolucionDeFacturacion panel;
	
	public PanelDevolucionDeFacturacion getPanel() {
		return panel;
	}

	public JInternalFrameBodegaDevolucionesDeClientes(ControladorVentanaDevolucionesFacturadas controlador) {
		setBounds(100, 100, 450, 300);
		setTitle("DEVOLUCION DE FACTURAS");
		
		panel = new PanelDevolucionDeFacturacion();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setControlador(controlador);
	}

}
