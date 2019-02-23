package vistaProveedores;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;

import controladorVistaPrincipal.ControladorVentanaProveedores;
import modeloEntidades.Socio;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.ScrollPane;

import net.miginfocom.swing.MigLayout;
import vista.Panel_BotonesCRUD;
import vista.Panel_DireccionTelefono;

import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JSpinner;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JDesktopPane;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import javax.swing.JInternalFrame;
import javax.swing.JScrollBar;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class Panel_Proveedores extends JPanel {
	public Panel_BotonesCRUD panel_datosProveedor;
	public Panel_DireccionTelefono panel_direccionTelefono;
	public JPanel panelContact;
	public JPanel panel_superior;
	public Panel_DatosContacto panel_contactos;
	
	
	/**
	 * Create the panel.
	 */
	public Panel_Proveedores() {
		setLayout(new BorderLayout(0, 0));
		
		panelContact = new JPanel();
		panelContact.setPreferredSize(new Dimension(280, 100));
		panelContact.setBorder(new TitledBorder(null, "Contactos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelContact, BorderLayout.WEST);
		panelContact.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		
		
		panel_contactos = new Panel_DatosContacto();
		panelContact.add(panel_contactos);
		
		panel_superior = new JPanel();
		add(panel_superior, BorderLayout.NORTH);
		panel_superior.setLayout(new GridLayout(0, 2, 20, 5));
		
		panel_datosProveedor = new Panel_BotonesCRUD();
		panel_datosProveedor.setBorder(new TitledBorder(null, "Datos Proveedor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_superior.add(panel_datosProveedor);
	
		panel_direccionTelefono = new Panel_DireccionTelefono();
		panel_direccionTelefono.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Direcci\u00F3n - Tel\u00E9fono", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_superior.add(panel_direccionTelefono);
		
	

	}
	

	
	
	
	
	public void setControlador(ControladorVentanaProveedores controlador) {
		
		
		panel_datosProveedor.setControlador(controlador);
		panel_contactos.setControlador(controlador);
		panel_direccionTelefono.setControlador(controlador);
	}
}
