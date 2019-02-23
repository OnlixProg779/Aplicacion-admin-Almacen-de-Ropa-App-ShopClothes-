package vistaCuentas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import controladorVistaCuentas.ControladorVentanaCuentasCheques;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class JInternalFrameCuentasCheques extends JInternalFrame {
	private PanelCheques panel;
		
	public PanelCheques getPanel() {
		return panel;
	}
	
	

	public JInternalFrameCuentasCheques(ControladorVentanaCuentasCheques controlador) {
		setBounds(100, 100, 604, 413);
		setTitle("CHEQUES");
		
		panel = new PanelCheques();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setControlador(controlador);
	}

}
