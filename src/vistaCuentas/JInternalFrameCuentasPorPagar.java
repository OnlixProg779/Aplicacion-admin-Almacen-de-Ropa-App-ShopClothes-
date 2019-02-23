package vistaCuentas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import controladorVistaCuentas.ControladorVentanaCuentasPorPagar;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class JInternalFrameCuentasPorPagar extends JInternalFrame {
	private PanelCxP panel;
	
	public PanelCxP getPanel() {
		return panel;
	}


	public JInternalFrameCuentasPorPagar(ControladorVentanaCuentasPorPagar controlador) {
		setBounds(100, 100, 450, 300);
		setTitle("CUENTAS POR PAGAR");
		
		panel = new PanelCxP();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setControlador(controlador);
	}

}
