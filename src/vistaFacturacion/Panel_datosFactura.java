package vistaFacturacion;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import controladorVistaFacturacion.ControladorVentanaNuevaFactura;

import javax.swing.SwingConstants;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import java.awt.Color;

public class Panel_datosFactura extends JPanel {
	public JTextField txtCedula;
	public JTextField txtNombre;
	public JTextField txtDireccion;
	public JTextField txtTelefono;
	private JButton btnNuevoCliente;
	private JButton btnGuardar;
	private JButton btnImprimir;
	private JButton btnBuscarCliente;
	public JCheckBox chckbxCredito;

	/**
	 * Create the panel.
	 */
	
	
	public Panel_datosFactura() {
		setLayout(new GridLayout(0, 4, 5, 5));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(10, 0));
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.CENTER);
		
		JLabel lblTelefono = new JLabel("Telefono:   ");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblCedula = new JLabel("Cedula:   ");
		lblCedula.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCedula.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_4.add(lblCedula);
		
		JLabel lblNombre = new JLabel("Nombre:   ");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_4.add(lblNombre);
		
		JLabel lblDireccion = new JLabel("Direccion:   ");
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccion.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_4.add(lblDireccion);
		panel_4.add(lblTelefono);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 5, 5));
		
		JPanel panel_9 = new JPanel();
		panel_1.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		btnBuscarCliente = new JButton("");
		btnBuscarCliente.setIcon(new ImageIcon(Panel_datosFactura.class.getResource("/Imagenes/Search 16.png")));
		panel_9.add(btnBuscarCliente, BorderLayout.EAST);
		
		txtCedula = new JTextField();
		txtCedula.setDisabledTextColor(Color.BLACK);
		panel_9.add(txtCedula, BorderLayout.CENTER);
		txtCedula.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtCedula.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setDisabledTextColor(Color.BLACK);
		txtNombre.setEnabled(false);
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_1.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setDisabledTextColor(Color.BLACK);
		txtDireccion.setEnabled(false);
		txtDireccion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_1.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setDisabledTextColor(Color.BLACK);
		txtTelefono.setEnabled(false);
		txtTelefono.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_1.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_2.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		chckbxCredito = new JCheckBox("Credito");
		chckbxCredito.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxCredito.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		panel_7.add(chckbxCredito);
		
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8);
		panel_8.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		btnNuevoCliente = new JButton("Nuevo Cliente ");
		btnNuevoCliente.setVisible(false);
		btnNuevoCliente.setEnabled(false);
		
		panel_8.add(btnNuevoCliente);
		btnNuevoCliente.setIcon(new ImageIcon(Panel_datosFactura.class.getResource("/Imagenes/group_2-new 24x24.png")));
		btnNuevoCliente.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(100, 100));
		add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnGuardar = new JButton("Guardar ");
		btnGuardar.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnGuardar.setIcon(new ImageIcon(Panel_datosFactura.class.getResource("/Imagenes/save 24x24.png")));
		panel_6.add(btnGuardar);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnImprimir.setIcon(new ImageIcon(Panel_datosFactura.class.getResource("/Imagenes/database-print 24x24.png")));
		panel_5.add(btnImprimir);
		btnGuardar.setActionCommand("guardar factura");
		btnImprimir.setActionCommand("imprimir factura");
		btnNuevoCliente.setActionCommand("añadir nuevo cliente");
		btnBuscarCliente.setActionCommand("buscar Cliente");
	//	chckbxCredito.setActionCommand("");
		
	}
	
	public JButton getBtnImprimir() {
		return btnImprimir;
	}

	public JButton getBtnNuevoCliente() {
		return btnNuevoCliente;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public void setControlador(ControladorVentanaNuevaFactura controlador) {
		btnGuardar.addActionListener(controlador);
		btnImprimir.addActionListener(controlador);
		btnNuevoCliente.addActionListener(controlador);
		btnBuscarCliente.addActionListener(controlador);
	}
}
