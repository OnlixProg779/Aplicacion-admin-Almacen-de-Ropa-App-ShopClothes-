package vistaBodega;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import controladorBodega.ControladorBodegaDAO;
import controladorVistaBodega.ControladorVentanaBodegaRevisar;
import modeloEntidades.Categoria;
import modeloEntidades.Socio;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JComboBox;

public class Panel_Bodega extends JPanel {
	public JTable tableProductos;
	private JButton btnEliminar;
	private JButton btnEditarNombre;
	public JTextField txtNombre;
	private JButton btnEditarCategotia;
	public JTextField txtCodigo;
	private JButton btnActualizar;
	private JButton btnIrCodigo;
	private JButton btnIrNombre;
	private JButton btnBusquedaAvanzada;
	private JButton btnBuscarAvanzada;
	private JPanel panel_7;
	
	//public ControladorBodegaDAO controladorBodegaDAO;
	
	private JScrollPane scrollPane;
	public JComboBox<Categoria> comboBoxCategoria;
	public JComboBox<Socio> comboBoxSocio;

	/**
	 * Create the panel.
	 */
	public Panel_Bodega() {
		setLayout(new BorderLayout(0, 0));
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Busqueda en Bodega", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 4, 7, 0));
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		JLabel lblNombre = new JLabel("Nombre:  ");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		
		JLabel lblTipo = new JLabel("Categoria");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipo.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		panel_3.setLayout(new GridLayout(0, 1, 0, 5));
		
		JLabel lblCodigo = new JLabel("Codigo:  ");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodigo.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		panel_3.add(lblCodigo);
		panel_3.add(lblNombre);
		panel_3.add(lblTipo);
		
		JLabel lblNewLabel = new JLabel("Socio:  ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		panel_3.add(lblNewLabel);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		panel_4.setLayout(new GridLayout(0, 1, 0, 5));
		
		txtCodigo = new JTextField();
		panel_4.add(txtCodigo);
		txtCodigo.setColumns(10);
		panel_4.add(txtNombre);
		
		comboBoxCategoria = new JComboBox();
		panel_4.add(comboBoxCategoria);
		
		comboBoxSocio = new JComboBox();
		panel_4.add(comboBoxSocio);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		
		btnBusquedaAvanzada = new JButton("Buscar");
		btnBusquedaAvanzada.setIcon(new ImageIcon(Panel_Bodega.class.getResource("/Imagenes/SearchFolderHS.png")));
		btnBusquedaAvanzada.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_5.setLayout(new GridLayout(0, 1, 0, 5));
		
		btnIrCodigo = new JButton("Ir");
		btnIrCodigo.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnIrCodigo.setIcon(new ImageIcon(Panel_Bodega.class.getResource("/Imagenes/Search 16.png")));
		panel_5.add(btnIrCodigo);
		
		btnBuscarAvanzada = new JButton("Buscar");
		btnBuscarAvanzada.setEnabled(false);
		btnBuscarAvanzada.setVisible(false);
		
		btnIrNombre = new JButton("Ir");
		btnIrNombre.setVisible(false);
		btnIrNombre.setEnabled(false);
		btnIrNombre.setIcon(new ImageIcon(Panel_Bodega.class.getResource("/Imagenes/Search 16.png")));
		btnIrNombre.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_5.add(btnIrNombre);
		btnIrNombre.setActionCommand("buscar por nombre");
		btnBuscarAvanzada.setIcon(new ImageIcon(Panel_Bodega.class.getResource("/Imagenes/Search 16.png")));
		btnBuscarAvanzada.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_5.add(btnBuscarAvanzada);
		btnBuscarAvanzada.setActionCommand("buscar en busqueda avanzada");
		panel_5.add(btnBusquedaAvanzada);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.WEST);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setVisible(false);
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		
		JButton btnNewButton_2 = new JButton("New button");
		
		
		btnNewButton_2.setVisible(false);
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_1.setLayout(new GridLayout(0, 1, 5, 5));
		
		btnEditarCategotia = new JButton("Editar Categoria");
		btnEditarCategotia.setIcon(new ImageIcon(Panel_Bodega.class.getResource("/Imagenes/Stock Index Down 16x16.png")));
		btnEditarCategotia.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_1.add(btnEditarCategotia);
		
		btnEditarNombre = new JButton("Editar Nombre");
		btnEditarNombre.setIcon(new ImageIcon(Panel_Bodega.class.getResource("/Imagenes/EditTableHS.png")));
		btnEditarNombre.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_1.add(btnEditarNombre);
		
		btnEliminar = new JButton("Eliminar Producto");
		btnEliminar.setEnabled(false);
		btnEliminar.setVisible(false);
		btnEliminar.setIcon(new ImageIcon(Panel_Bodega.class.getResource("/Imagenes/XSDSchema_RemoveAllButSelectionFromWorkspaceCmd.png")));
		btnEliminar.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_1.add(btnEliminar);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setEnabled(false);
		btnNewButton.setVisible(false);
		panel_1.add(btnNewButton);
		panel_1.add(btnNewButton_1);
		panel_1.add(btnNewButton_2);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);
		btnBusquedaAvanzada.setActionCommand("activar busqueda avanzada");
		btnEditarCategotia.setActionCommand("Editar Categoria");
		btnEliminar.setActionCommand("Eliminar Producto");
		btnIrCodigo.setActionCommand("buscar por codigo");
		
		panel_7 = new JPanel();
		panel.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		
		tableProductos = new JTable();
		
		
		
		scrollPane.setViewportView(tableProductos);
		
		btnActualizar = new JButton("");
		panel_7.add(btnActualizar);
		btnActualizar.setPreferredSize(new Dimension(56, 56));
		btnActualizar.setIcon(new ImageIcon(Panel_Bodega.class.getResource("/Imagenes/Reload 48.png")));
		
		btnActualizar.setActionCommand("actualizar estado de botones por default");
		btnEditarNombre.setActionCommand("modificar nombre");
		
		
		
		
		
	}
	
	
	public void setControlador(ControladorVentanaBodegaRevisar controlador) {
		btnActualizar.addActionListener(controlador);
		btnBuscarAvanzada.addActionListener(controlador);
		btnBusquedaAvanzada.addActionListener(controlador);
		btnEditarCategotia.addActionListener(controlador);
		btnEliminar.addActionListener(controlador);
		btnIrCodigo.addActionListener(controlador);
		btnIrNombre.addActionListener(controlador);
		btnEditarNombre.addActionListener(controlador);
		
		
	}
	
	
	
}
