package vistaCuentas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import controladorCuentas.ControladorCuentasChequesDAO;
import controladorVistaCuentas.ControladorVentanaCuentasCheques;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

public class PanelCheques extends JPanel {
	private JButton btnBuscarAvanzado;
	private JButton btnBuscar;
	private JButton btnIngresar;
	private JButton btnEliminar;
	public JDateChooser dateChooserFechaDesde;
	public JDateChooser dateChooserFechaBusqueda;
	public JDateChooser dateChooserFechaHasta;
	private JButton btnActualizar;
	//public ControladorCuentasChequesDAO controladorCuentasChequesDAO;
	public JTable table;
	private JPanel panelRangoFechas;
	private JButton btnNuevoCheque;
	private JPanel panelBotones;
	
	private static PanelCheques panelCheques;
	private static JFrame nuevo;
	private JButton btnActualizar_1;
	private JButton btnGenerarReporte;
	
	public static JFrame getNuevo() {
		return nuevo;
	}

	public static void setNuevo(JFrame nuevo) {
		PanelCheques.nuevo = nuevo;
	}

	
	
	
	public static PanelCheques getPanelCheques() {
		return panelCheques;
	}

	public static  void setPanelCheques(PanelCheques panelCheques) {
		PanelCheques.panelCheques = panelCheques;
	}

	/**
	 * Create the panel.
	 */
	
	
	public PanelCheques() {
		setLayout(new BorderLayout(0, 0));
		setName("PANEL DE CHEQUES");
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_10 = new JPanel();
		panel_3.add(panel_10);
		
		JPanel panel_7 = new JPanel();
		panel_3.add(panel_7);
		
		JLabel lblCheques = new JLabel("Cheques");
		panel_7.add(lblCheques);
		lblCheques.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		
		JPanel panel = new JPanel();
		panel_3.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnGenerarReporte = new JButton("");
		btnGenerarReporte.setIcon(new ImageIcon(PanelCheques.class.getResource("/Imagenes/report-print 32x32.png")));
		panel.add(btnGenerarReporte);
		
		btnActualizar_1 = new JButton("");
		panel.add(btnActualizar_1);
		btnActualizar_1.setIcon(new ImageIcon(PanelCheques.class.getResource("/Imagenes/Reload 32.png")));
		btnActualizar_1.setActionCommand("Actualizar");
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 35, 5));
		
		JLabel lblSaldo = new JLabel("Saldo: ");
		lblSaldo.setVisible(false);
		lblSaldo.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_4.add(lblSaldo);
		
		JLabel label = new JLabel("$$,$$");
		label.setVisible(false);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		panel_4.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cheques", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(5, 5));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JLabel lblFecha = new JLabel(" Fecha de Deposito:");
		lblFecha.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_6.add(lblFecha);
		
		dateChooserFechaBusqueda = new JDateChooser();
		dateChooserFechaBusqueda.setPreferredSize(new Dimension(130, 20));
		dateChooserFechaBusqueda.setDateFormatString("yyyy-MM-dd");
		panel_6.add(dateChooserFechaBusqueda);
		
		btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(PanelBancos.class.getResource("/Imagenes/Search 16.png")));
		panel_6.add(btnBuscar);
		
		panelRangoFechas = new JPanel();
		panel_5.add(panelRangoFechas, BorderLayout.CENTER);
		panelRangoFechas.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JPanel panel_8 = new JPanel();
		panelRangoFechas.add(panel_8);
		panel_8.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JLabel label_2 = new JLabel("Desde:");
		label_2.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_8.add(label_2);
		
		dateChooserFechaDesde = new JDateChooser();
		dateChooserFechaDesde.setPreferredSize(new Dimension(130, 20));
		dateChooserFechaDesde.setDateFormatString("yyyy-MM-dd");
		panel_8.add(dateChooserFechaDesde);
		
		JPanel panel_9 = new JPanel();
		panelRangoFechas.add(panel_9);
		panel_9.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JLabel label_3 = new JLabel("Hasta:");
		label_3.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_9.add(label_3);
		
		dateChooserFechaHasta = new JDateChooser();
		dateChooserFechaHasta.setPreferredSize(new Dimension(130, 20));
		dateChooserFechaHasta.setDateFormatString("yyyy-MM-dd");
		panel_9.add(dateChooserFechaHasta);
		
		btnBuscarAvanzado = new JButton("");
		btnBuscarAvanzado.setIcon(new ImageIcon(PanelBancos.class.getResource("/Imagenes/Search 16.png")));
		panel_9.add(btnBuscarAvanzado);
		
		panelBotones = new JPanel();
		add(panelBotones, BorderLayout.SOUTH);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.setVisible(false);
		btnIngresar.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panelBotones.add(btnIngresar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setVisible(false);
		btnEliminar.setEnabled(false);
		btnEliminar.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panelBotones.add(btnEliminar);
		
		btnActualizar = new JButton("Editar");
		btnActualizar.setVisible(false);
		btnActualizar.setEnabled(false);
		btnActualizar.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panelBotones.add(btnActualizar);
		
		btnActualizar.setActionCommand("Actualizar cheque");
		btnBuscar.setActionCommand("buscar cheques por fecha");
		
		btnNuevoCheque = new JButton("Nuevo Cheque");
		btnNuevoCheque.setIcon(new ImageIcon(PanelCheques.class.getResource("/Imagenes/Plus 16x16.png")));
		btnNuevoCheque.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_6.add(btnNuevoCheque);
		btnBuscarAvanzado.setActionCommand("buscar cheques por rango de fechas");
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		btnEliminar.setActionCommand("eliminar cheque");
		btnIngresar.setActionCommand("ingresar cheque");
		btnNuevoCheque.setActionCommand("nuevo cheque");
		btnGenerarReporte.setActionCommand("Generar Reporte");
	}
	
	public JPanel getPanelBotones() {
		return panelBotones;
	}

	public JPanel getPanelRangoFechas() {
		return panelRangoFechas;
	}

	public JButton getBtnNuevoCheque() {
		return btnNuevoCheque;
	}

	public void setControlador(ControladorVentanaCuentasCheques controlador) {
		btnGenerarReporte.addActionListener(controlador);
		table.addMouseListener(controlador);
		btnActualizar.addActionListener(controlador);
		btnBuscar.addActionListener(controlador);
		btnBuscarAvanzado.addActionListener(controlador);
		btnEliminar.addActionListener(controlador);
		btnIngresar.addActionListener(controlador);
		btnNuevoCheque.addActionListener(controlador);
		btnActualizar_1.addActionListener(controlador);
		
	}



}
