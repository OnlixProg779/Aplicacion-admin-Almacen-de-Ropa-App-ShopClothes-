package vistaSocios;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import controladorSocios.ControladorSociosVerSociosDAO;
import controladorVistaSocios.ControladorVentanaSociosVerSocios;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;

public class PanelSocioVerSocio extends JPanel {
	public JTextField txtRuc;
	public JTextField txtNombre;
	public JTextField txtReferencia;
	private JButton btnNuevo;
	private JButton btnModificar;
	private JButton btnEliminar;
	public JTable table;
	public JList JlistTelefonos;
	private JTextField txtTelefono;
	private JButton btnTelfono;
	
	//public ControladorSociosVerSociosDAO controladorSociosVerSociosDAO;
	
	private JButton btnSearch;
	private JButton btnActualizar;

	/**
	 * Create the panel.
	 */
	public PanelSocioVerSocio() {
		setLayout(new BorderLayout(0, 5));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Tabla de Socios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		JlistTelefonos = new JList();
		JlistTelefonos.setBorder(new TitledBorder(null, "Tel\u00E9fonos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setRowHeaderView(JlistTelefonos);
		
		table = new JTable();
		
		
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Datos Socio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_9, BorderLayout.NORTH);
		panel_9.setLayout(new BorderLayout(10, 10));
		
		JPanel panel_10 = new JPanel();
		panel_9.add(panel_10, BorderLayout.WEST);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_10.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new GridLayout(0, 1, 5, 5));
		
		btnNuevo = new JButton(" Nuevo");
		btnNuevo.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnNuevo.setIcon(new ImageIcon(PanelSocioVerSocio.class.getResource("/Imagenes/vendors-new 24x24.png")));
		panel_5.add(btnNuevo);
		
		btnModificar = new JButton(" Modificar");
		btnModificar.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnModificar.setIcon(new ImageIcon(PanelSocioVerSocio.class.getResource("/Imagenes/vendors-edit 24x24.png")));
		panel_5.add(btnModificar);
		
		btnTelfono = new JButton("Tel\u00E9fono");
		btnTelfono.setIcon(new ImageIcon(PanelSocioVerSocio.class.getResource("/Imagenes/phone-add_16x16.png")));
		btnTelfono.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_5.add(btnTelfono);
		
		btnTelfono.setActionCommand("nuevo telefono");
		
		btnEliminar = new JButton(" Eliminar");
		btnEliminar.setVisible(false);
		btnEliminar.setEnabled(false);
		btnEliminar.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnEliminar.setIcon(new ImageIcon(PanelSocioVerSocio.class.getResource("/Imagenes/vendors-remove 24x24.png")));
		panel_5.add(btnEliminar);
		
		JPanel panel_11 = new JPanel();
		panel_9.add(panel_11, BorderLayout.CENTER);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_11.add(panel_1, BorderLayout.CENTER);
		panel_1.setPreferredSize(new Dimension(10, 100));
		panel_1.setLayout(new GridLayout(0, 2, 10, 0));
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 3, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Ruc: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_6.add(lblNewLabel);
		
		txtRuc = new JTextField();
		panel_6.add(txtRuc);
		txtRuc.setColumns(10);
		
		JPanel panel_12 = new JPanel();
		panel_6.add(panel_12);
		panel_12.setLayout(null);
		
		btnSearch = new JButton("");
		btnSearch.setEnabled(false);
		btnSearch.setVisible(false);
		btnSearch.setBounds(0, 0, 57, 33);
		panel_12.add(btnSearch);
		btnSearch.setIcon(new ImageIcon(PanelSocioVerSocio.class.getResource("/Imagenes/search-new 24x24.png")));
		
		JLabel lblNewLabel_1 = new JLabel("    Nombre: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_6.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		panel_6.add(txtNombre);
		txtNombre.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_6.add(panel_4);
		
		JLabel lblReferencia = new JLabel("       Referencia: ");
		lblReferencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblReferencia.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_6.add(lblReferencia);
		
		txtReferencia = new JTextField();
		panel_6.add(txtReferencia);
		txtReferencia.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
		
		JLabel lblTelfono = new JLabel("    Tel\u00E9fono: ");
		lblTelfono.setEnabled(false);
		lblTelfono.setVisible(false);
		lblTelfono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelfono.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_6.add(lblTelfono);
		
		txtTelefono = new JTextField();
		txtTelefono.setEnabled(false);
		txtTelefono.setVisible(false);
		txtTelefono.setColumns(10);
		panel_6.add(txtTelefono);
		
		JPanel panel_8 = new JPanel();
		panel_8.setVisible(false);
		panel_6.add(panel_8);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel panel_13 = new JPanel();
		panel_3.add(panel_13);
		
		JPanel panel_14 = new JPanel();
		panel_3.add(panel_14);
		
		JPanel panel_15 = new JPanel();
		panel_3.add(panel_15);
		
		JPanel panel_16 = new JPanel();
		panel_3.add(panel_16);
		panel_16.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		
		btnActualizar = new JButton("");
		btnActualizar.setPreferredSize(new Dimension(33, 33));
		panel_16.add(btnActualizar);
		btnActualizar.setIcon(new ImageIcon(PanelSocioVerSocio.class.getResource("/Imagenes/112_RefreshArrow_Blue_16x16_72.png")));
		
		btnEliminar.setActionCommand("Eliminar Socio");
		btnModificar.setActionCommand("Modificar Socio");
		btnNuevo.setActionCommand("Nuevo Socio");
		

		btnSearch.setActionCommand("buscar ruc");
		btnActualizar.setActionCommand("actualizar paguina");
		
		
		
	}
	
	public void setControlador(ControladorVentanaSociosVerSocios controlador) {
		txtNombre.addKeyListener(controlador);
		table.addMouseListener(controlador);
		btnEliminar.addActionListener(controlador);
		btnSearch.addActionListener(controlador);
		btnActualizar.addActionListener(controlador);
		
		
		btnModificar.addActionListener(controlador);
		btnNuevo.addActionListener(controlador);
		btnTelfono.addActionListener(controlador);
	}
}
