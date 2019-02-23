package vistaCuentas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import controladorVistaCuentas.ControladorVentanaCuentasDepositos;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class JInternalFrameCuentasDepositos extends JInternalFrame {
	private PanelDepositos panel;

	
	
	public PanelDepositos getPanel() {
		return panel;
	}



	public JInternalFrameCuentasDepositos(ControladorVentanaCuentasDepositos controlador) {
		setBounds(100, 100, 450, 300);
		setTitle("DEPOSITOS");
		
		panel = new PanelDepositos();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setControlador(controlador);
	}

}
