package vistaBodega;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import controladorVistaBodega.ControladorVentanaSociosDevoluciones;

import java.awt.BorderLayout;

public class JInternalFrameBodegaDevolucionesASocios extends JInternalFrame {

	
	public JInternalFrameBodegaDevolucionesASocios(ControladorVentanaSociosDevoluciones controlador) {
		setBounds(100, 100, 450, 300);
		setTitle("DEVOLUCIONES A SOCIOS");
		
		PanelDevolucionASocios panel = new PanelDevolucionASocios();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setControlador(controlador);
	}

}
