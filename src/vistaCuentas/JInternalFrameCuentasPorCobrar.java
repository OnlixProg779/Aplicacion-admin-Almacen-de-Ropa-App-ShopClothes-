package vistaCuentas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import controladorVistaCuentas.ControladorVentanaCuentasPorCobrar;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class JInternalFrameCuentasPorCobrar extends JInternalFrame {
	private PanelCxC panel;

	
	
	public PanelCxC getPanel() {
		return panel;
	}



	public JInternalFrameCuentasPorCobrar(ControladorVentanaCuentasPorCobrar controlador) {
		setBounds(100, 100, 450, 300);
		setTitle("CUENTAS POR COBRAR");
		
		panel = new PanelCxC();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setControlador(controlador);
	}

}