package vistaCuentas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import controladorVistaCuentas.ControladorVentanaCuentasCaja;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class JInternalFrameCuentasCaja extends JInternalFrame {
	private PanelCaja panel;


	public PanelCaja getPanel() {
		return panel;
	}


	/**
	 * Create the frame.
	 */
	public JInternalFrameCuentasCaja(ControladorVentanaCuentasCaja controlador) {
		setBounds(100, 100, 736, 421);
		setTitle("CAJA");
		
		panel = new PanelCaja();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		panel.setControlador(controlador);
		
	}

}
