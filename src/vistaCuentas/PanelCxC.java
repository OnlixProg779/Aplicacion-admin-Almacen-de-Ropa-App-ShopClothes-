package vistaCuentas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import controladorCuentas.ControladorCuentasCuentasPorCobrarDAO;
import controladorReportes.ControladorReportesCuentasCobradasDAO;
import controladorVistaCuentas.ControladorVentanaCuentasPorCobrar;

import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;

public class PanelCxC extends JPanel {
	private JButton btnBuscarRango;
	public JDateChooser dateChooser_1;
	public JDateChooser dateChooser_2;
	private JButton btnPagar;
	public JTextField txtCedula;
	private JButton btnBuscarCed;
	private JButton btnAbonar;
	
	public JTable table;
	private JPanel panel;
	private JPanel panel_4;
	private JLabel lblTitulo;
	
	//public  ControladorCuentasCuentasPorCobrarDAO controladorCuentasCuentasPorCobrarDAO;
	//public ControladorReportesCuentasCobradasDAO controladorReportesCuentasCobradasDAO;
	
	
	private JPanel panel_1;
	private JButton btnActualizar;
	
	
	public JPanel getPanel_1() {
		return panel_1;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JPanel getPanel_4() {
		return panel_4;
	}

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	/**
	 * Create the panel.
	 */
	public PanelCxC() {
setLayout(new BorderLayout(0, 0));

		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_3.add(panel_14);
		
		JPanel panel_13 = new JPanel();
		panel_3.add(panel_13);
		
		lblTitulo = new JLabel("Cuentas por Cobrar");
		panel_13.add(lblTitulo);
		lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		
		JPanel panel_12 = new JPanel();
		panel_3.add(panel_12);
		panel_12.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnActualizar = new JButton("");
		panel_12.add(btnActualizar);
		btnActualizar.setIcon(new ImageIcon(PanelCxC.class.getResource("/Imagenes/112_RefreshArrow_Blue_16x16_72.png")));
		btnActualizar.setActionCommand("Actualizar");
		
		panel_4 = new JPanel();
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
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cuentas por Cobrar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(5, 5));
		
		JPanel panel_6 = new JPanel();
		
		panel_5.add(panel_6, BorderLayout.NORTH);
		
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JPanel panel_8 = new JPanel();
		panel_8.setVisible(false);
		panel_7.add(panel_8);
		panel_8.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JLabel label_2 = new JLabel("Desde:");
		label_2.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_8.add(label_2);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setPreferredSize(new Dimension(130, 20));
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		panel_8.add(dateChooser_1);
		
		JPanel panel_9 = new JPanel();
		panel_9.setVisible(false);
		panel_7.add(panel_9);
		panel_9.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JLabel label_3 = new JLabel("Hasta:");
		label_3.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_9.add(label_3);
		
		dateChooser_2 = new JDateChooser();
		dateChooser_2.setPreferredSize(new Dimension(130, 20));
		dateChooser_2.setDateFormatString("yyyy-MM-dd");
		panel_9.add(dateChooser_2);
		
		btnBuscarRango = new JButton("");
		btnBuscarRango.setIcon(new ImageIcon(PanelBancos.class.getResource("/Imagenes/Search 16.png")));
		panel_9.add(btnBuscarRango);
		
		panel = new JPanel();
		panel_7.add(panel);
		panel.setLayout(new GridLayout(0, 2, 5, 5));
		
		btnPagar = new JButton("Pagar ");
		btnPagar.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel.add(btnPagar);
		
		btnAbonar = new JButton("Abonar");
		btnAbonar.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel.add(btnAbonar);
		panel_6.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JPanel panel_10 = new JPanel();
		panel_6.add(panel_10);
		panel_10.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblFecha = new JLabel(" Cedula:");
		panel_10.add(lblFecha);
		lblFecha.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		txtCedula = new JTextField(30);
		panel_10.add(txtCedula);
		txtCedula.setSize(new Dimension(130, 20));
		txtCedula.setPreferredSize(new Dimension(130, 20));
		txtCedula.setColumns(10);
		
		btnBuscarCed = new JButton("");
		panel_10.add(btnBuscarCed);
		btnBuscarCed.setIcon(new ImageIcon(PanelCxC.class.getResource("/Imagenes/Search 16.png")));
		btnBuscarCed.setActionCommand("buscar cedula");
		
		JButton btnNewButton_1 = new JButton("New button");
		panel_10.add(btnNewButton_1);
		btnNewButton_1.setVisible(false);
		btnNewButton_1.setEnabled(false);
		
		JButton btnNewButton_2 = new JButton("New button");
		panel_10.add(btnNewButton_2);
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setVisible(false);
		btnPagar.setActionCommand("Pagar cuenta");
		btnBuscarRango.setActionCommand("Buscar en rango");
		btnAbonar.setActionCommand("Abonar a la cuenta");
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	
	public <T> void setControlador(T controlador) {
		btnBuscarCed.addActionListener((ActionListener) controlador);
		btnPagar.addActionListener((ActionListener) controlador);
		btnBuscarRango.addActionListener((ActionListener) controlador);
		btnAbonar.addActionListener((ActionListener) controlador);
		btnActualizar.addActionListener((ActionListener) controlador);
	}

}
