package vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import controladorVistaBodega.ControladorVentanaNotaDeEntrega;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

public class Panel_AddPrductosATabla extends JPanel {
	public JTextField txtProductoBusqueda;
	public JTextField txtProductoAdd;
	public JTextField txtPrecioAdd;
	public JTextField txtCantidadAdd;
	private JButton btnAddProductoACompra;
	public JTable tableProductos;
	public JLabel lblCostoCu;

	/**
	 * Create the panel.
	 */
	public Panel_AddPrductosATabla() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 30));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(5, 5));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		
		JLabel label = new JLabel("Producto");
		label.setEnabled(false);
		label.setVisible(false);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_1.add(label);
		
		txtProductoBusqueda = new JTextField();
		txtProductoBusqueda.setEnabled(false);
		txtProductoBusqueda.setVisible(false);
		txtProductoBusqueda.setColumns(15);
		panel_1.add(txtProductoBusqueda);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setVisible(false);
		btnNewButton.setEnabled(false);
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(5, 5));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(0, 3, 5, 5));
		
		JLabel label_1 = new JLabel("Producto");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_3.add(label_1);
		
		lblCostoCu = new JLabel("Costo c/u");
		lblCostoCu.setHorizontalAlignment(SwingConstants.CENTER);
		lblCostoCu.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_3.add(lblCostoCu);
		
		JLabel label_3 = new JLabel("Cantidad");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_3.add(label_3);
		
		txtProductoAdd = new JTextField();
		txtProductoAdd.setColumns(10);
		panel_3.add(txtProductoAdd);
		
		txtPrecioAdd = new JTextField();
		txtPrecioAdd.setColumns(10);
		panel_3.add(txtPrecioAdd);
		
		txtCantidadAdd = new JTextField();
		txtCantidadAdd.setColumns(10);
		panel_3.add(txtCantidadAdd);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.EAST);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnAddProductoACompra = new JButton("");
		btnAddProductoACompra.setIcon(new ImageIcon(Panel_AddPrductosATabla.class.getResource("/Imagenes/005_Task_24x24_72.png")));
		panel_4.add(btnAddProductoACompra);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		tableProductos = new JTable();
		scrollPane.setViewportView(tableProductos);
		
		btnAddProductoACompra.setActionCommand("Add producto a Tabla");
		

	}
	
	public <T> void setControlador(T controlador) {
		
		btnAddProductoACompra.addActionListener((ActionListener) controlador);
		tableProductos.addMouseListener((MouseListener) controlador);
		//btnNuevoProducto.addActionListener((ActionListener) controlador);
		
	}

}
