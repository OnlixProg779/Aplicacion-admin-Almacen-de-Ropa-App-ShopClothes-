package vistaCuentas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import controladorCuentas.ControladorCuentasCajaDAO;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JScrollPane;

public class PanelCaja extends JPanel {
	public JTable table;
	private JButton btnBuscar;
	private JButton btnBuscarAvanzado;
	public JDateChooser dateChooserFechaDesde;
	public JDateChooser dateChooserFechaBusqueda;
	public JDateChooser dateChooserFechaHasta;
	public JLabel lblCajaActual;
	public JLabel lblFechaActual;
	private JButton btnNuevoIngreso;
	private JButton btnNuevoEgreso;
	private JButton btnActualizar;
	
	//public ControladorCuentasCajaDAO controladorCuentasCajaDAO;

	/**
	 * Create the panel.
	 */
	public PanelCaja() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(5, 5));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(5, 5));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_17 = new JPanel();
		panel_3.add(panel_17);
		
		JPanel panel_16 = new JPanel();
		panel_3.add(panel_16);
		
		lblFechaActual = new JLabel("Fecha Actual");
		panel_16.add(lblFechaActual);
		lblFechaActual.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblFechaActual.setText(LocalDate.now().toString());
		
		JPanel panel_15 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_15.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_3.add(panel_15);
		
		btnActualizar = new JButton("");
		panel_15.add(btnActualizar);
		btnActualizar.setIcon(new ImageIcon(PanelCaja.class.getResource("/Imagenes/112_RefreshArrow_Blue_16x16_72.png")));
		btnActualizar.setActionCommand("Actualizar");
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(0, 2, 55, 5));
		
		JLabel lblCajaActual_1 = new JLabel("Caja USD $");
		lblCajaActual_1.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblCajaActual_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(lblCajaActual_1);
		
		lblCajaActual = new JLabel("$$,$$");
		lblCajaActual.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblCajaActual.setHorizontalAlignment(SwingConstants.LEFT);
		panel_4.add(lblCajaActual);
		
		JPanel panel_12 = new JPanel();
		panel_4.add(panel_12);
		
		JPanel panel_13 = new JPanel();
		panel_4.add(panel_13);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new GridLayout(0, 2, 5, 5));
		
		JPanel panel_6 = new JPanel();
		panel_6.setPreferredSize(new Dimension(343, 160));
		panel_6.setBorder(new TitledBorder(null, "Buscar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8, BorderLayout.NORTH);
		panel_8.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JLabel lblFecha = new JLabel("  Fecha:");
		lblFecha.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_8.add(lblFecha);
		
		dateChooserFechaBusqueda = new JDateChooser();
		dateChooserFechaBusqueda.setPreferredSize(new Dimension(130, 20));
		dateChooserFechaBusqueda.setDateFormatString("yyyy-MM-dd");
		
		panel_8.add(dateChooserFechaBusqueda);
		
		btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(PanelCaja.class.getResource("/Imagenes/Search 16.png")));
		panel_8.add(btnBuscar);
		
		JPanel panel_9 = new JPanel();
		panel_6.add(panel_9, BorderLayout.CENTER);
		panel_9.setBorder(new TitledBorder(null, "En Rango", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JPanel panel_10 = new JPanel();
		panel_9.add(panel_10);
		panel_10.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_10.add(lblDesde);
		
		dateChooserFechaDesde = new JDateChooser();
		dateChooserFechaDesde.setPreferredSize(new Dimension(130, 20));
		dateChooserFechaDesde.setDateFormatString("yyyy-MM-dd");
		panel_10.add(dateChooserFechaDesde);
		
		JPanel panel_11 = new JPanel();
		panel_9.add(panel_11);
		panel_11.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_11.add(lblHasta);
		
		dateChooserFechaHasta = new JDateChooser();
		dateChooserFechaHasta.setPreferredSize(new Dimension(130, 20));
		dateChooserFechaHasta.setDateFormatString("yyyy-MM-dd");
		panel_11.add(dateChooserFechaHasta);
		
		btnBuscarAvanzado = new JButton("");
		btnBuscarAvanzado.setIcon(new ImageIcon(PanelCaja.class.getResource("/Imagenes/Search 16.png")));
		panel_11.add(btnBuscarAvanzado);
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 3, 7, 7));
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setVisible(false);
		panel_7.add(btnNewButton_1);
		
		btnNuevoIngreso = new JButton("Nuevo Ingreso");
		btnNuevoIngreso.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_7.add(btnNuevoIngreso);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setVisible(false);
		
		JPanel panel_14 = new JPanel();
		panel_7.add(panel_14);
		panel_14.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel_7.add(btnNewButton_2);
		
		btnNuevoEgreso = new JButton("Retiro");
		btnNuevoEgreso.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_7.add(btnNuevoEgreso);
		
		JButton button_7 = new JButton("New button");
		button_7.setEnabled(false);
		button_7.setVisible(false);
		panel_7.add(button_7);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.setVisible(false);
		panel_7.add(btnNewButton_3);
		
		JButton button_2 = new JButton("New button");
		button_2.setEnabled(false);
		button_2.setVisible(false);
		panel_7.add(button_2);
		
		JButton button_6 = new JButton("New button");
		button_6.setEnabled(false);
		button_6.setVisible(false);
		panel_7.add(button_6);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setEnabled(false);
		btnNewButton_4.setVisible(false);
		panel_7.add(btnNewButton_4);
		
		JButton button_1 = new JButton("New button");
		button_1.setEnabled(false);
		button_1.setVisible(false);
		panel_7.add(button_1);
		
		JButton button_5 = new JButton("New button");
		button_5.setEnabled(false);
		button_5.setVisible(false);
		panel_7.add(button_5);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Movimientos en Caja", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnBuscar.setActionCommand("buscar por fecha");
		btnBuscarAvanzado.setActionCommand("buscar por fecha avanzado");
		btnNuevoIngreso.setActionCommand("Nuevo Registro");
		btnNuevoEgreso.setActionCommand("Retiro");
	}
	
	public void setControlador(controladorVistaCuentas.ControladorVentanaCuentasCaja controlador) {
		
		btnBuscar.addActionListener(controlador);
		btnBuscarAvanzado.addActionListener(controlador);
		btnNuevoIngreso.addActionListener(controlador);
		btnNuevoEgreso.addActionListener(controlador);
		btnActualizar.addActionListener(controlador);
		
	}
}
