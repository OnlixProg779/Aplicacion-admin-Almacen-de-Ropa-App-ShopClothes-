package vistaBodega;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import controladorVistaBodega.ControladorVentanaDevolucionesFacturadas;
import vista.Panel_AddPrductosATabla;

import javax.swing.UIManager;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class PanelDevolucionDeFacturacion extends JPanel {
	public JTextField txtNumeroFactura;
	public JTextField txtSubTotal;
	public JTextField txtTotal;
	public JTextField txtIva;
	private JTable table_Devolucion;
	private JTable table_ProductosDeFactura;
	public JLabel lblProducto;
	public JLabel lblPrecio;
	public JTextField txtCantidad;
	private JButton btnGuardar;
	private JButton btnEliminarItem;
	private JButton btnDisminuirCantidadAItem;
	private JButton btnAddDevolucion;
	private JButton btnBuscar;
	public JLabel lblFechaFactura;
	public JCheckBox chckbxDevolucionDeIva;
	public JLabel lblRuc;
	public JLabel lblNombre;
	public JTextField txtRazon;
	private JLabel lblRazon;
	/**
	 * Create the panel.
	 */
	
	
	public PanelDevolucionDeFacturacion() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(10, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		
		JLabel lblIngresoDeFacturas = new JLabel("Devoluci\u00F3n de Facturas");
		lblIngresoDeFacturas.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		panel_2.add(lblIngresoDeFacturas);
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(10, 90));
		panel_1.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JLabel label = new JLabel(" N\u00B0 Factura ");
		label.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_5.add(label);
		
		txtNumeroFactura = new JTextField();
		txtNumeroFactura.setColumns(17);
		panel_5.add(txtNumeroFactura);
		
		btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(PanelDevolucionDeFacturacion.class.getResource("/Imagenes/Search 16.png")));
		btnBuscar.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_5.add(btnBuscar);
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JLabel lblFecha = new JLabel(" Fecha ");
		lblFecha.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_4.add(lblFecha);
		
		lblFechaFactura = new JLabel("yyyy-MM-dd");
		lblFechaFactura.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_4.add(lblFechaFactura);
		
		chckbxDevolucionDeIva = new JCheckBox("Devolucion de Iva ");
		chckbxDevolucionDeIva.setEnabled(false);
		chckbxDevolucionDeIva.setVisible(false);
		chckbxDevolucionDeIva.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_4.add(chckbxDevolucionDeIva);
		
		JPanel panel_22 = new JPanel();
		panel_22.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(panel_22, BorderLayout.CENTER);
		panel_22.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_22.add(panel_11, BorderLayout.CENTER);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_11.add(scrollPane_1, BorderLayout.CENTER);
		
		table_Devolucion = new JTable();
		scrollPane_1.setViewportView(table_Devolucion);
		
		JPanel panel = new JPanel();
		panel_22.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_55 = new JPanel();
		panel.add(panel_55, BorderLayout.EAST);
		panel_55.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNuevoCliente_1 = new JButton("Nuevo Cliente");
		panel_55.add(btnNuevoCliente_1);
		btnNuevoCliente_1.setEnabled(false);
		btnNuevoCliente_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		
		JPanel panel_33 = new JPanel();
		panel.add(panel_33, BorderLayout.CENTER);
		panel_33.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_44 = new JPanel();
		panel_33.add(panel_44, BorderLayout.EAST);
		
		txtSubTotal = new JTextField();
		txtSubTotal.setVisible(false);
		txtSubTotal.setEnabled(false);
		txtSubTotal.setBorder(null);
		txtSubTotal.setColumns(10);
		
		txtTotal = new JTextField();
		txtTotal.setVisible(false);
		txtTotal.setEnabled(false);
		txtTotal.setBorder(null);
		txtTotal.setColumns(10);
		panel_44.setLayout(new GridLayout(0, 1, 0, 0));
		panel_44.add(txtSubTotal);
		
		txtIva = new JTextField();
		txtIva.setVisible(false);
		txtIva.setEnabled(false);
		txtIva.setBorder(null);
		panel_44.add(txtIva);
		txtIva.setColumns(10);
		panel_44.add(txtTotal);
		
		JPanel panel_7 = new JPanel();
		panel_33.add(panel_7, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Sub Total:  ");
		lblNewLabel.setVisible(false);
		lblNewLabel.setEnabled(false);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		
		JLabel lblNewLabel_1 = new JLabel("Total:  ");
		lblNewLabel_1.setVisible(false);
		lblNewLabel_1.setEnabled(false);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		panel_7.setLayout(new GridLayout(0, 1, 0, 0));
		panel_7.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Iva 12%:  ");
		lblNewLabel_2.setVisible(false);
		lblNewLabel_2.setEnabled(false);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		panel_7.add(lblNewLabel_2);
		panel_7.add(lblNewLabel_1);
		btnNuevoCliente_1.setVisible(false);
		
		JPanel panel_6 = new JPanel();
		panel_22.add(panel_6, BorderLayout.EAST);
		panel_6.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_6.add(panel_14);
		panel_14.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnDisminuirCantidadAItem = new JButton("Disminuir");
		btnDisminuirCantidadAItem.setVisible(false);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon(PanelDevolucionDeFacturacion.class.getResource("/Imagenes/check-save 24x24.png")));
		panel_14.add(btnGuardar);
		btnGuardar.setActionCommand("guardar devolucion");
		btnDisminuirCantidadAItem.setEnabled(false);
		btnDisminuirCantidadAItem.setIcon(new ImageIcon(PanelDevolucionDeFacturacion.class.getResource("/Imagenes/Stock Index Down 16x16.png")));
		panel_14.add(btnDisminuirCantidadAItem);
		
		btnEliminarItem = new JButton("Eliminar");
		btnEliminarItem.setVisible(false);
		btnEliminarItem.setEnabled(false);
		btnEliminarItem.setIcon(new ImageIcon(PanelDevolucionDeFacturacion.class.getResource("/Imagenes/XSDSchema_RemoveAllButSelectionFromWorkspaceCmd.png")));
		panel_14.add(btnEliminarItem);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setVisible(false);
		btnNewButton_4.setEnabled(false);
		panel_14.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.setVisible(false);
		btnNewButton_5.setEnabled(false);
		panel_14.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("New button");
		btnNewButton_6.setVisible(false);
		btnNewButton_6.setEnabled(false);
		panel_14.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("New button");
		btnNewButton_7.setVisible(false);
		btnNewButton_7.setEnabled(false);
		panel_14.add(btnNewButton_7);
		
		JPanel panel_8 = new JPanel();
		add(panel_8, BorderLayout.WEST);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_8.add(panel_9, BorderLayout.CENTER);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_9.add(scrollPane, BorderLayout.CENTER);
		
		table_ProductosDeFactura = new JTable();
		scrollPane.setViewportView(table_ProductosDeFactura);
		
		JPanel panel_12 = new JPanel();
		panel_8.add(panel_12, BorderLayout.SOUTH);
		panel_12.setLayout(new BorderLayout(0, 5));
		
		JPanel panel_10 = new JPanel();
		panel_12.add(panel_10, BorderLayout.CENTER);
		panel_10.setLayout(new GridLayout(0, 3, 5, 5));
		
		JLabel lblNewLabel_3 = new JLabel("Producto");
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_10.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Precio c/u");
		lblNewLabel_4.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_10.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Cantidad");
		lblNewLabel_5.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_10.add(lblNewLabel_5);
		
		lblProducto = new JLabel("-");
		lblProducto.setHorizontalAlignment(SwingConstants.CENTER);
		panel_10.add(lblProducto);
		
		lblPrecio = new JLabel("-");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		panel_10.add(lblPrecio);
		
		txtCantidad = new JTextField();
		txtCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		panel_10.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		JPanel panel_13 = new JPanel();
		panel_12.add(panel_13, BorderLayout.EAST);
		panel_13.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnAddDevolucion = new JButton("");
		panel_13.add(btnAddDevolucion);
		btnAddDevolucion.setIcon(new ImageIcon(PanelDevolucionDeFacturacion.class.getResource("/Imagenes/005_Task_24x24_72.png")));
		
		btnAddDevolucion.setActionCommand("add a tabla devolucion");
		
		JPanel panel_15 = new JPanel();
		panel_12.add(panel_15, BorderLayout.SOUTH);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		lblRazon = new JLabel("Razon: ");
		lblRazon.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_15.add(lblRazon, BorderLayout.WEST);
		
		txtRazon = new JTextField();
		panel_15.add(txtRazon, BorderLayout.CENTER);
		txtRazon.setColumns(10);
		btnBuscar.setActionCommand("buscar numero de factura");
		
		lblNombre = new JLabel("**");
		lblNombre.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_5.add(lblNombre);
		
		lblRuc = new JLabel("**");
		lblRuc.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_5.add(lblRuc);
		btnDisminuirCantidadAItem.setActionCommand("disminuir cantidad");
		btnEliminarItem.setActionCommand("Eliminar Item");
		
		
	}

	

	public JTable getTable_Devolucion() {
		return table_Devolucion;
	}



	public JTable getTable_ProductosDeFactura() {
		return table_ProductosDeFactura;
	}



	public void setControlador(ControladorVentanaDevolucionesFacturadas controlador) {
		btnAddDevolucion.addActionListener(controlador);
		btnBuscar.addActionListener(controlador);
		btnDisminuirCantidadAItem.addActionListener(controlador);
		btnGuardar.addActionListener(controlador);
		btnEliminarItem.addActionListener(controlador);
		table_ProductosDeFactura.addMouseListener(controlador);
	}
}
