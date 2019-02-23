package vistaCuentas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import controladorCuentas.ControladorCuentasCuentasPorPagarDAO;
import controladorReportes.ControladorReportesCuentasPagadasDAO;
import controladorVistaCuentas.ControladorVentanaCuentasPorPagar;
import javax.swing.JScrollPane;

public class PanelCxP extends JPanel {
	private JButton btnBuscarEnRango;
	public JDateChooser dateChooserFechaDesde;
	public JDateChooser dateChooserFechaHasta;
	private JButton btnPagar;
	private JButton btnAbonar;
	private JLabel label;
	private JButton btnNuevaCuenta;

	public JTable table;
	private JLabel lblTitulo;
	private JPanel panel_4;
	private JPanel panel;
	
//	public ControladorCuentasCuentasPorPagarDAO controladorCuentasCuentasPorPagarDAO;
//	public ControladorReportesCuentasPagadasDAO controladorReportesCuentasPagadasDAO;
	
	private JPanel panel_1;
	private JButton btnActualizar;
	private JPanel panel_6;
	private JPanel panel_10;
	private JPanel panel_11;
	private JPanel panel_12;
	private JPanel panel_13;
	
	
	
	public JPanel getPanel_1() {
		return panel_1;
	}

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public JPanel getPanel_4() {
		return panel_4;
	}

	public JPanel getPanel() {
		return panel;
	}

	/**
	 * Create the panel.
	 */
	public PanelCxP() {
setLayout(new BorderLayout(0, 0));

		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new GridLayout(0, 3, 0, 0));
		
		panel_13 = new JPanel();
		panel_3.add(panel_13);
		
		panel_12 = new JPanel();
		panel_3.add(panel_12);
		
		lblTitulo = new JLabel("Cuentas por Pagar");
		panel_12.add(lblTitulo);
		lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		
		panel_11 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_11.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_3.add(panel_11);
		
		btnActualizar = new JButton("");
		panel_11.add(btnActualizar);
		btnActualizar.setIcon(new ImageIcon(PanelCxP.class.getResource("/Imagenes/112_RefreshArrow_Blue_16x16_72.png")));
		btnActualizar.setActionCommand("Actualizar");
		
		panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cuentas por Pagar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(5, 5));
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8);
		panel_8.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JLabel label_2 = new JLabel("Desde:");
		label_2.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_8.add(label_2);
		
		dateChooserFechaDesde = new JDateChooser();
		dateChooserFechaDesde.setPreferredSize(new Dimension(130, 20));
		dateChooserFechaDesde.setDateFormatString("yyyy-MM-dd");
		panel_8.add(dateChooserFechaDesde);
		
		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9);
		panel_9.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JLabel label_3 = new JLabel("Hasta:");
		label_3.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_9.add(label_3);
		
		dateChooserFechaHasta = new JDateChooser();
		dateChooserFechaHasta.setPreferredSize(new Dimension(130, 20));
		dateChooserFechaHasta.setDateFormatString("yyyy-MM-dd");
		panel_9.add(dateChooserFechaHasta);
		
		btnBuscarEnRango = new JButton("");
		btnBuscarEnRango.setIcon(new ImageIcon(PanelBancos.class.getResource("/Imagenes/Search 16.png")));
		panel_9.add(btnBuscarEnRango);
		
		panel = new JPanel();
		panel_7.add(panel);
		panel.setLayout(new GridLayout(0, 2, 5, 5));
		
		btnPagar = new JButton("Pagar ");
		btnPagar.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel.add(btnPagar);
		
		btnAbonar = new JButton("Abonar");
		btnAbonar.setEnabled(false);
		btnAbonar.setVisible(false);
		btnAbonar.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel.add(btnAbonar);
		
		btnAbonar.setActionCommand("abonar a la cuenta");
		btnBuscarEnRango.setActionCommand("buscar en rango");
		btnPagar.setActionCommand("Pagar Cuenta");
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel_6 = new JPanel();
		panel_4.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblRegistrar = new JLabel("  Registrar");
		panel_6.add(lblRegistrar);
		lblRegistrar.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		btnNuevaCuenta = new JButton("Nueva Cuenta");
		panel_6.add(btnNuevaCuenta);
		btnNuevaCuenta.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnNuevaCuenta.setActionCommand("Nueva Cuenta");
		
		JLabel lblSaldo = new JLabel("Saldo: ");
		panel_6.add(lblSaldo);
		lblSaldo.setVisible(false);
		lblSaldo.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		label = new JLabel("$$,$$");
		panel_6.add(label);
		label.setVisible(false);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		
		panel_10 = new JPanel();
		panel_4.add(panel_10);
		panel_10.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
	}
	
	public <T> void setControlador(T controlador) {
		
		btnAbonar.addActionListener((ActionListener) controlador);
		btnBuscarEnRango.addActionListener((ActionListener) controlador);
		btnPagar.addActionListener((ActionListener) controlador);
		btnNuevaCuenta.addActionListener((ActionListener) controlador);
		btnActualizar.addActionListener((ActionListener)controlador);
		
	}

}
