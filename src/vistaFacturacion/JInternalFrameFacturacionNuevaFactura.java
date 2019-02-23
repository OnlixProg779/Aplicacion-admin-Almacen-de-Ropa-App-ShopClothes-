package vistaFacturacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import controladorVistaFacturacion.ControladorVentanaNuevaFactura;

import java.awt.BorderLayout;

public class JInternalFrameFacturacionNuevaFactura extends JInternalFrame {
	private Panel_Factura panelFactura;


	public Panel_Factura getPanelFactura() {
		return panelFactura;
	}


	/**
	 * Create the frame.
	 */
	public JInternalFrameFacturacionNuevaFactura(ControladorVentanaNuevaFactura controlador) {
		setBounds(100, 100, 595, 407);
		
		panelFactura = new Panel_Factura();
		getContentPane().add(panelFactura, BorderLayout.CENTER);
		setTitle("NUEVA FACTURA");
		panelFactura.setControlador(controlador);
	}

}
