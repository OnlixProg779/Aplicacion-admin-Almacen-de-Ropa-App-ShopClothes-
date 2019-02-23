package vistaClientes;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import controladorClientes.ControladorClientesDAO;
import controladorVistaClientes.ControladorVentanaClientesVer;
import vista.Panel_DireccionTelefono;
import vista.Panel_Discapacidad;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Panel_Clientes extends JPanel {
	private Panel_llenarClienteDatos panelDatosGenerales;
	private JPanel panelTablaDeClientes;
	private JScrollPane scrollPane;
	public JTable table;

	//public ControladorClientesDAO controladorClientesDAO; 
	
	public Panel_llenarClienteDatos getPanelDatosGenerales() {
		return panelDatosGenerales;
	}

	public JPanel getPanelTablaDeClientes() {
		return panelTablaDeClientes;
	}

	/**
	 * Create the panel.
	 */
	public Panel_Clientes() {
		setLayout(new BorderLayout(0, 0));
		
		panelDatosGenerales = new Panel_llenarClienteDatos();
		panelDatosGenerales.setBorder(new TitledBorder(null, "Datos Generales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelDatosGenerales, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		panelTablaDeClientes = new JPanel();
		panelTablaDeClientes.setBorder(new TitledBorder(null, "Tabla de Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelTablaDeClientes, BorderLayout.CENTER);
		panelTablaDeClientes.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panelTablaDeClientes.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);

	}
	
	public void setControlador (ControladorVentanaClientesVer controlador) {
		table.addMouseListener(controlador);
		panelDatosGenerales.setControlador(controlador);
		
	}

}
