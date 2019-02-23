package vistaCuentas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladorVistaCuentas.ControladorVentanaCuentasBancos;
import modeloEntidades.Banco;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class FrameNuevaCuenta extends JFrame {

	private JPanel contentPane;
	public JTextField txtNcuenta;
	public JComboBox<Banco> comboBoxBanco;
	public JTextField txtTitular;
	public JComboBox comboBoxTipo;
	private JButton btnGuardar;

	
	public FrameNuevaCuenta() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNuevaCuentaBancaria = new JLabel("Nueva Cuenta Bancaria");
		lblNuevaCuentaBancaria.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		panel.add(lblNuevaCuentaBancaria);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_1.add(btnGuardar);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 2, 15, 7));
		
		JLabel lblNewLabel = new JLabel("Banco:");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblNewLabel);
		
		comboBoxBanco = new JComboBox();
		panel_2.add(comboBoxBanco);
		
		JLabel lblTitular = new JLabel("Titular: ");
		lblTitular.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblTitular.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblTitular);
		
		txtTitular = new JTextField();
		panel_2.add(txtTitular);
		txtTitular.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo: ");
		lblTipo.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblTipo.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblTipo);
		
		comboBoxTipo = new JComboBox();
		comboBoxTipo.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>", "CUENTA AHORROS", "CUENTA CORRIENTE"}));
		panel_2.add(comboBoxTipo);
		
		JLabel lblNumeroDeCuenta = new JLabel("N\u00B0 De Cuenta: ");
		lblNumeroDeCuenta.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblNumeroDeCuenta.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblNumeroDeCuenta);
		
		txtNcuenta = new JTextField();
		panel_2.add(txtNcuenta);
		txtNcuenta.setColumns(10);
		btnGuardar.setActionCommand("Guardar Cuenta");
	}
	
	public void setControlador(ControladorVentanaCuentasBancos controlador) {
		btnGuardar.addActionListener(controlador);
		
	}
}
