package vistaSocios;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import controladorVistaSocios.ControladorVentanaSociosVerSocios;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class JInternalFrameSociosVerSocios extends JInternalFrame {
	private PanelSocioVerSocio panel;

	
	public PanelSocioVerSocio getPanel() {
		return panel;
	}


	public JInternalFrameSociosVerSocios(ControladorVentanaSociosVerSocios controlador) {
		setBounds(100, 100, 450, 300);
		setTitle("SOCIOS");
		
		panel = new PanelSocioVerSocio();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setControlador(controlador);
	}

}
