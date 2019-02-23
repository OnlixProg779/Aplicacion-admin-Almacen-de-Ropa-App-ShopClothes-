package vistaBodega;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.border.TitledBorder;

import controladorVistaBodega.ControladorVentanaNotaDeEntrega;
import modeloEntidades.Socio;
import vista.Panel_AddPrductosATabla;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;

public class Panel_ingresarNotaDeEntrega extends JPanel {
	public JTextField txtN_Nota;
	public JTextField txtReceptor;
	public JTextField txtValorTotal;
	private JButton btnGuardarCompra;
	private JButton btnIngresarNuevoProducto;
	private Panel_AddPrductosATabla panelAddProductos;
	public JComboBox<Socio> comboBoxSocios;
	public JDateChooser dateChooserFecha;
	public JTable table_1;

	
	public Panel_AddPrductosATabla getPanelAddProductos() {
		return panelAddProductos;
	}

	/**
	 * Create the panel.
	 */
	public Panel_ingresarNotaDeEntrega() {
		setLayout(new BorderLayout(5, 5));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(400, 10));
		add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(10, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		
		JLabel lblIngresoDeFacturas = new JLabel("Nota de Entrega");
		lblIngresoDeFacturas.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		panel_2.add(lblIngresoDeFacturas);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.WEST);
		
		JLabel lblNewLabel = new JLabel("Socio");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		JLabel lblNFactura = new JLabel("N\u00B0 Nota E");
		lblNFactura.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNFactura.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		JLabel lblIva = new JLabel("Receptor");
		lblIva.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIva.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		JLabel lblValorTotal = new JLabel("Valor total");
		lblValorTotal.setEnabled(false);
		lblValorTotal.setVisible(false);
		lblValorTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorTotal.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_3.setLayout(new GridLayout(0, 1, 5, 5));
		panel_3.add(lblNewLabel);
		panel_3.add(lblNFactura);
		panel_3.add(lblFecha);
		panel_3.add(lblIva);
		panel_3.add(lblValorTotal);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.CENTER);
		
		txtN_Nota = new JTextField();
		txtN_Nota.setColumns(10);
		
		txtValorTotal = new JTextField();
		txtValorTotal.setVisible(false);
		txtValorTotal.setEnabled(false);
		txtValorTotal.setColumns(10);
		panel_4.setLayout(new GridLayout(0, 1, 5, 5));
		
		comboBoxSocios = new JComboBox();
		comboBoxSocios.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>"}));
		panel_4.add(comboBoxSocios);
		panel_4.add(txtN_Nota);
		
		dateChooserFecha = new JDateChooser();
		dateChooserFecha.setDateFormatString("yyyy-MM-dd");
		panel_4.add(dateChooserFecha);
		
		txtReceptor = new JTextField();
		txtReceptor.setColumns(10);
		panel_4.add(txtReceptor);
		panel_4.add(txtValorTotal);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.EAST);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnIngresarNuevoProducto = new JButton("");
		btnIngresarNuevoProducto.setToolTipText("A\u00F1adir un nuevo proveedor");
		btnIngresarNuevoProducto.setForeground(Color.WHITE);
		btnIngresarNuevoProducto.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnIngresarNuevoProducto.setPreferredSize(new Dimension(50, 23));
		btnIngresarNuevoProducto.setMinimumSize(new Dimension(23, 23));
		btnIngresarNuevoProducto.setIcon(new ImageIcon(Panel_ingresarNotaDeEntrega.class.getResource("/Imagenes/Plus 16x16.png")));
		panel_5.add(btnIngresarNuevoProducto);
		
		JLabel lblNuevoProducto = new JLabel("<html> Nuevo Producto<html>");
		lblNuevoProducto.setVerticalAlignment(SwingConstants.TOP);
		lblNuevoProducto.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lblNuevoProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevoProducto.setPreferredSize(new Dimension(54, 14));
		panel_5.add(lblNuevoProducto);
		
		btnGuardarCompra = new JButton("");
		btnGuardarCompra.setToolTipText("Registrar Nota de Entrega");
		btnGuardarCompra.setIcon(new ImageIcon(Panel_ingresarNotaDeEntrega.class.getResource("/Imagenes/check-save 24x24.png")));
		panel_5.add(btnGuardarCompra);
		
		panelAddProductos = new Panel_AddPrductosATabla();
		panelAddProductos.setBorder(new TitledBorder(null, "Productos (Socio)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panelAddProductos, BorderLayout.CENTER);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new TitledBorder(null, "Nota De Entrega (Productos)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_11, BorderLayout.CENTER);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_11.add(scrollPane, BorderLayout.CENTER);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		btnGuardarCompra.setActionCommand("Guardar Nota Entrega");
		btnIngresarNuevoProducto.setActionCommand("Ingresar Nuevo Producto");

	}
	
	public void setControlador(ControladorVentanaNotaDeEntrega controlador) {
		
		btnGuardarCompra.addActionListener(controlador);
		btnIngresarNuevoProducto.addActionListener(controlador);
		comboBoxSocios.addItemListener(controlador);
		panelAddProductos.setControlador(controlador);
		
	}
}
