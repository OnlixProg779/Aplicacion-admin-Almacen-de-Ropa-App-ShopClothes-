package vistaCuentas;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import com.toedter.calendar.JDateChooser;

import controladorCuentas.ControladorCuentasBancosDAO;
import controladorVistaCuentas.ControladorVentanaCuentasBancos;
import modeloEntidades.Banco;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;

public class PanelBancos extends JPanel {
	public JComboBox<Banco> comboBoxBanco;
	private JButton btnBuscarFechaAvanzada;
	private JButton btnBuscarFecha;
	private JButton btnDeposito;
	private JButton btnRetiro;
	public JDateChooser dateChooserDesde;
	public JDateChooser dateChooserFechaBusqueda;
	public JDateChooser dateChooserHasta;
	public JTable table;
	public JList listNCuentas;
	private JButton btnNuevaCuenta;
	private JButton btnActualizar;

	//public ControladorCuentasBancosDAO controladorCuentasBancosDAO;
	/**
	 * Create the panel.
	 */
	public PanelBancos() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_13 = new JPanel();
		panel_3.add(panel_13);
		
		JPanel panel_12 = new JPanel();
		panel_3.add(panel_12);
		
		JLabel lblBanco = new JLabel("Banco: ");
		panel_12.add(lblBanco);
		lblBanco.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		
		comboBoxBanco = new JComboBox<>();
		panel_12.add(comboBoxBanco);
		
		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_11.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_3.add(panel_11);
		
		btnActualizar = new JButton("");
		panel_11.add(btnActualizar);
		btnActualizar.setIcon(new ImageIcon(PanelBancos.class.getResource("/Imagenes/112_RefreshArrow_Blue_16x16_72.png")));
		btnActualizar.setActionCommand("Actualizar");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Movimientos Bancarios", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		listNCuentas = new JList();
		listNCuentas.setBorder(new TitledBorder(null, "Cuentas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setRowHeaderView(listNCuentas);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(5, 5));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, BorderLayout.NORTH);
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8);
		panel_8.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JLabel label_2 = new JLabel("Desde:");
		label_2.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_8.add(label_2);
		
		dateChooserDesde = new JDateChooser();
		dateChooserDesde.setPreferredSize(new Dimension(130, 20));
		dateChooserDesde.setDateFormatString("yyyy-MM-dd");
		panel_8.add(dateChooserDesde);
		
		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9);
		panel_9.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JLabel label_3 = new JLabel("Hasta:");
		label_3.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_9.add(label_3);
		
		dateChooserHasta = new JDateChooser();
		dateChooserHasta.setPreferredSize(new Dimension(130, 20));
		dateChooserHasta.setDateFormatString("yyyy-MM-dd");
		panel_9.add(dateChooserHasta);
		
		btnBuscarFechaAvanzada = new JButton("");
		btnBuscarFechaAvanzada.setIcon(new ImageIcon(PanelBancos.class.getResource("/Imagenes/Search 16.png")));
		panel_9.add(btnBuscarFechaAvanzada);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		btnDeposito = new JButton("Deposito");
		btnDeposito.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel.add(btnDeposito);
		
		btnRetiro = new JButton("Retiro");
		btnRetiro.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel.add(btnRetiro);
		btnBuscarFechaAvanzada.setActionCommand("Buscar Fecha Avanzada");
		btnDeposito.setActionCommand("Nuevo deposito");
		btnRetiro.setActionCommand("Nuevo retiro");
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_6.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblFecha = new JLabel(" Fecha:");
		panel_4.add(lblFecha);
		lblFecha.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		dateChooserFechaBusqueda = new JDateChooser();
		panel_4.add(dateChooserFechaBusqueda);
		dateChooserFechaBusqueda.setPreferredSize(new Dimension(130, 20));
		dateChooserFechaBusqueda.setDateFormatString("yyyy-MM-dd");
		
		btnBuscarFecha = new JButton("");
		panel_4.add(btnBuscarFecha);
		btnBuscarFecha.setIcon(new ImageIcon(PanelBancos.class.getResource("/Imagenes/Search 16.png")));
		
		btnBuscarFecha.setActionCommand("Buscar Fecha");
		
		btnNuevaCuenta = new JButton("Nueva Cuenta");
		panel_4.add(btnNuevaCuenta);
		btnNuevaCuenta.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnNuevaCuenta.setActionCommand("nueva cuenta");
		
		JPanel panel_10 = new JPanel();
		panel_6.add(panel_10);
		panel_10.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

	}
	
	public void setControlador(ControladorVentanaCuentasBancos controlador) {
		btnBuscarFecha.addActionListener(controlador);
		btnBuscarFechaAvanzada.addActionListener(controlador);
		btnDeposito.addActionListener(controlador);
		btnRetiro.addActionListener(controlador);
		comboBoxBanco.addItemListener(controlador);
		listNCuentas.addMouseListener(controlador);
		btnNuevaCuenta.addActionListener(controlador);
		btnActualizar.addActionListener(controlador);
	}
}
