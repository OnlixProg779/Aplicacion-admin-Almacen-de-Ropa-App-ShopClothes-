package vistaCuentas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import controladorVistaCuentas.ControladorVentanaCuentasBancos;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class JInternalFrameCuentasBancos extends JInternalFrame {
	private PanelBancos panel;

	

	public PanelBancos getPanel() {
		return panel;
	}



	public JInternalFrameCuentasBancos(ControladorVentanaCuentasBancos controlador) {
		setBounds(100, 100, 450, 300);
		setTitle("BANCOS");
		
		panel = new PanelBancos();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setControlador(controlador);
	}

}
