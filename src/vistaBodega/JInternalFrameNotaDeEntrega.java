package vistaBodega;



import javax.swing.JInternalFrame;


import controladorVistaBodega.ControladorVentanaNotaDeEntrega;

import java.awt.BorderLayout;

public class JInternalFrameNotaDeEntrega extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -682226092119090890L;
	private Panel_ingresarNotaDeEntrega panel;


	public Panel_ingresarNotaDeEntrega getPanel() {
		return panel;
	}


	/**
	 * Create the frame.
	 */
	public JInternalFrameNotaDeEntrega(ControladorVentanaNotaDeEntrega controlador) {
		setBounds(100, 100, 690, 476);
		setTitle("NOTA DE ENTREGA");
		
		panel = new Panel_ingresarNotaDeEntrega();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setControlador(controlador);
	}

}
