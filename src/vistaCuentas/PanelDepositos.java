package vistaCuentas;

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

import controladorCuentas.ControladorCuentasDepositosDAO;
import controladorVistaCuentas.ControladorVentanaCuentasDepositos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

public class PanelDepositos extends JPanel {
	private JButton bntBuscarEnRango;
	public JDateChooser dateChooserFechaDesde;
	public JDateChooser dateChooserFechaHasta;
	private JButton btnNuevoDeposito;
	//public ControladorCuentasDepositosDAO controladorCuentasDepositosDAO;
	public JTable table;
	private JButton btnActualizar;
	/**
	 * Create the panel.
	 */
	public PanelDepositos() {
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
		
		JLabel lblDepositos = new JLabel("Depositos");
		panel_12.add(lblDepositos);
		lblDepositos.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		
		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_11.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_3.add(panel_11);
		
		btnActualizar = new JButton("");
		panel_11.add(btnActualizar);
		btnActualizar.setIcon(new ImageIcon(PanelDepositos.class.getResource("/Imagenes/112_RefreshArrow_Blue_16x16_72.png")));
		btnActualizar.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnActualizar.setActionCommand("Actualizar");
		
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
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Depositos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(5, 5));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		panel_6.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		btnNuevoDeposito = new JButton("Nuevo Deposito");
		btnNuevoDeposito.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel.add(btnNuevoDeposito);
		
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
		
		bntBuscarEnRango = new JButton("");
		bntBuscarEnRango.setIcon(new ImageIcon(PanelBancos.class.getResource("/Imagenes/Search 16.png")));
		panel_9.add(bntBuscarEnRango);
		
		btnNuevoDeposito.setActionCommand("Nuevo Deposito");
		
		JPanel panel_10 = new JPanel();
		panel_6.add(panel_10);
		panel_10.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		bntBuscarEnRango.setActionCommand("Buscar en Rango");
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
	}
	
	public void setControlador(ControladorVentanaCuentasDepositos controlador) {
		
		btnNuevoDeposito.addActionListener(controlador);
		bntBuscarEnRango.addActionListener(controlador);
		btnActualizar.addActionListener(controlador);
	}

}
