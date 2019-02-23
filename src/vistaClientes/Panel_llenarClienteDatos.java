package vistaClientes;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.border.TitledBorder;

import controladorVistaClientes.ControladorVentanaClientesVer;
import modeloEntidades.Telefono;

import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class Panel_llenarClienteDatos extends JPanel {
	public JTextField txtCedula;
	public JTextField txtNombre;
	public JTextField txtApellido;
	public JTextField txtDireccion;
	private JButton btnNuevo;
	private JButton btnLimipar;
	private JButton btnModificar;
	private JButton btnEliminarCliente;
	private JButton btnBuscarCliente;
	public JTextField txtTelefono;
	private JPanel panel_2;
	private JPanel panel_7;
	private JPanel panel_8;
	private JButton btnGenerarReporte;
	private JButton btnActualizar;

	/**
	 * Create the panel.
	 */
	public Panel_llenarClienteDatos() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 5, 5));
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 5, 5));
		
		panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 5, 5));
		
		JLabel lblCedula = new JLabel("  Cedula:    ");
		panel_2.add(lblCedula, BorderLayout.WEST);
		lblCedula.setHorizontalAlignment(SwingConstants.CENTER);
		lblCedula.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		txtCedula = new JTextField();
		panel_2.add(txtCedula, BorderLayout.CENTER);
		txtCedula.setColumns(17);
		
		JLabel lblNombre = new JLabel("  Nombre:  ");
		panel_2.add(lblNombre);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		txtNombre = new JTextField();
		panel_2.add(txtNombre);
		txtNombre.setColumns(17);
		
		JLabel lblApellido = new JLabel(" Apellido:  ");
		panel_2.add(lblApellido);
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		txtApellido = new JTextField();
		panel_2.add(txtApellido);
		txtApellido.setColumns(17);
		
		JLabel lblDireccion = new JLabel(" Direcci\u00F3n:");
		panel_2.add(lblDireccion);
		lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccion.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		txtDireccion = new JTextField();
		panel_2.add(txtDireccion);
		txtDireccion.setColumns(17);
		
		JLabel lblTelfono = new JLabel("  Tel\u00E9fono:  ");
		panel_2.add(lblTelfono);
		lblTelfono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelfono.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		txtTelefono = new JTextField();
		panel_2.add(txtTelefono);
		txtTelefono.setColumns(17);
		
		panel_8 = new JPanel();
		panel_1.add(panel_8);
		panel_8.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 3));
		
		btnActualizar = new JButton("");
		btnActualizar.setIcon(new ImageIcon(Panel_llenarClienteDatos.class.getResource("/Imagenes/Reload 32.png")));
		panel_8.add(btnActualizar);
		btnActualizar.setActionCommand("Actualizar Paguina");
		
		btnGenerarReporte = new JButton("");
		btnGenerarReporte.setIcon(new ImageIcon(Panel_llenarClienteDatos.class.getResource("/Imagenes/report-print 32x32.png")));
		panel_8.add(btnGenerarReporte);
		btnGenerarReporte.setActionCommand("Generar Reporte");
		
		panel_7 = new JPanel();
		add(panel_7, BorderLayout.WEST);
		panel_7.setLayout(new GridLayout(0, 1, 5, 5));
		
		btnNuevo = new JButton("Nuevo");
		panel_7.add(btnNuevo);
		btnNuevo.setIcon(new ImageIcon(Panel_llenarClienteDatos.class.getResource("/Imagenes/customer-add 24x24.png")));
		btnNuevo.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		
		btnNuevo.setActionCommand("Nuevo Cliente");
		
		btnModificar = new JButton("Modificar");
		panel_7.add(btnModificar);
		btnModificar.setIcon(new ImageIcon(Panel_llenarClienteDatos.class.getResource("/Imagenes/customer-edit 24x24.png")));
		btnModificar.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnModificar.setActionCommand("Modificar cliente");
		
		btnLimipar = new JButton("Limpiar");
		btnLimipar.setIcon(new ImageIcon(Panel_llenarClienteDatos.class.getResource("/Imagenes/Clear 01 24x24.png")));
		panel_7.add(btnLimipar);
		btnLimipar.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnLimipar.setActionCommand("Limpiar");
		
		btnEliminarCliente = new JButton("Eliminar");
		btnEliminarCliente.setVisible(false);
		
		btnBuscarCliente = new JButton("Buscar");
		panel_7.add(btnBuscarCliente);
		btnBuscarCliente.setIcon(new ImageIcon(Panel_llenarClienteDatos.class.getResource("/Imagenes/customers-search 24x24.png")));
		btnBuscarCliente.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnBuscarCliente.setActionCommand("buscar Cliente");
		btnEliminarCliente.setEnabled(false);
		panel_7.add(btnEliminarCliente);
		btnEliminarCliente.setIcon(new ImageIcon(Panel_llenarClienteDatos.class.getResource("/Imagenes/customers-remove 24x24.png")));
		btnEliminarCliente.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnEliminarCliente.setActionCommand("Eliminar cliente");

	}
	
	public void setControlador(ControladorVentanaClientesVer controlador) {
		btnNuevo.addActionListener(controlador);
		btnLimipar.addActionListener(controlador);
		btnModificar.addActionListener(controlador);
		btnEliminarCliente.addActionListener(controlador);
		btnBuscarCliente.addActionListener(controlador);
		btnGenerarReporte.addActionListener(controlador);
		btnActualizar.addActionListener(controlador);
	
		
	}
	
	
}
