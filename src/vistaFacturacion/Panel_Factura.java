package vistaFacturacion;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controladorVistaFacturacion.ControladorVentanaNuevaFactura;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class Panel_Factura extends JPanel {
	private Panel_datosFactura panel_datosFactura;
	private Panel_TablaFacturaVentas panel_productos;
	private Panel_EncabezadoFactura panelEncabezado;

	
	
	public Panel_EncabezadoFactura getPanelEncabezado() {
		return panelEncabezado;
	}

	public Panel_datosFactura getPanel_datosFactura() {
		return panel_datosFactura;
	}

	public Panel_TablaFacturaVentas getPanel_productos() {
		return panel_productos;
	}

	/**
	 * Create the panel.
	 */
	public Panel_Factura() {
		setLayout(new BorderLayout(10, 7));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(10, 7));
		
		panelEncabezado = new Panel_EncabezadoFactura();
		panel.add(panelEncabezado, BorderLayout.NORTH);
		
		panel_datosFactura = new Panel_datosFactura();
		panel.add(panel_datosFactura, BorderLayout.SOUTH);
		
		panel_productos = new Panel_TablaFacturaVentas();
		add(panel_productos, BorderLayout.CENTER);
		
	}
	
	public void setControlador(ControladorVentanaNuevaFactura controlador) {
		
		panel_datosFactura.setControlador(controlador);
		panel_productos.setControlador(controlador);
		
	}
	
}
