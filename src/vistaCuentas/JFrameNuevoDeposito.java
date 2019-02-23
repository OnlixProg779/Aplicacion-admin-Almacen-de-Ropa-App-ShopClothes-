package vistaCuentas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladorVistaCuentas.ControladorVentanaIngresarDeposito;
import modeloEntidades.Cuenta;
import modeloEntidades.VistaCuenta;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class JFrameNuevoDeposito extends JFrame {

	private JPanel contentPane;
	public JTextField txtNDeposito;
	public JTextField txtEfectivo;
	public JTextField txtTotal;
	public JTextField txtDepositante;
	public JTextField txtDescripcion;
	private JButton btnGuardar;
	public JTextField txtCheque;
	private JButton btnBuscarCheque;
	public JDateChooser dateChooserFecha;
	public JTextField txtIdCheque;
	public JComboBox<VistaCuenta> comboBoxCuenta;
	


	public JButton getBtnBuscarCheque() {
		return btnBuscarCheque;
	}

	/**
	 * Create the frame.
	 */
	public JFrameNuevoDeposito(ControladorVentanaIngresarDeposito controlador) {
		setTitle("INGRESAR DEPOSITO");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(15, 15));
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new GridLayout(0, 2, 10, 10));
		
		JPanel panel = new JPanel();
		panel_3.add(panel);
		panel.setLayout(new BorderLayout(10, 0));
		
		JPanel panel_10 = new JPanel();
		panel.add(panel_10, BorderLayout.WEST);
		panel_10.setLayout(new GridLayout(0, 1, 10, 10));
		
		JLabel lblNDepsito = new JLabel("N\u00BA Dep\u00F3sito:");
		panel_10.add(lblNDepsito);
		lblNDepsito.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		JLabel lblNewLabel = new JLabel("Fecha:");
		panel_10.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		JLabel lblNumeroDeCuenta = new JLabel("N\u00B0 Cuenta:");
		panel_10.add(lblNumeroDeCuenta);
		lblNumeroDeCuenta.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		
		JPanel panel_11 = new JPanel();
		panel.add(panel_11);
		panel_11.setLayout(new GridLayout(0, 1, 10, 10));
		
		txtNDeposito = new JTextField();
		panel_11.add(txtNDeposito);
		txtNDeposito.setColumns(10);
		
		dateChooserFecha = new JDateChooser();
		panel_11.add(dateChooserFecha);
		dateChooserFecha.setDateFormatString("yyyy-MM-dd");
		
		comboBoxCuenta = new JComboBox<>();
		panel_11.add(comboBoxCuenta);
		
		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblEfectivo = new JLabel("     Efectivo:");
		lblEfectivo.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_1.add(lblEfectivo);
		
		txtEfectivo = new JTextField();
		
				
	
	
		panel_1.add(txtEfectivo);
		txtEfectivo.setColumns(10);
		
		JLabel lblCheque = new JLabel("     Cheque:");
		lblCheque.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_1.add(lblCheque);
		
		JPanel panel_9 = new JPanel();
		panel_1.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		btnBuscarCheque = new JButton("");
		btnBuscarCheque.setIcon(new ImageIcon(JFrameNuevoDeposito.class.getResource("/Imagenes/Search 16.png")));
		panel_9.add(btnBuscarCheque, BorderLayout.EAST);
		
		txtCheque = new JTextField();
		txtCheque.setEditable(false);
		panel_9.add(txtCheque, BorderLayout.CENTER);
		txtCheque.setColumns(10);
		
		JLabel lblTotal = new JLabel("     Total:");
		lblTotal.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_1.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setDisabledTextColor(Color.BLACK);
		txtTotal.setEnabled(false);
		panel_1.add(txtTotal);
		txtTotal.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.WEST);
		panel_4.setLayout(new GridLayout(0, 1, 10, 10));
		
		JLabel lblNewLabel_1 = new JLabel("Depositante:");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_4.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Descripcion:");
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_4.add(lblNewLabel_2);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLocation(10, 10);
		panel_2.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new GridLayout(0, 1, 10, 10));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		txtDepositante = new JTextField();
		panel_6.add(txtDepositante);
		txtDepositante.setColumns(20);
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		txtDescripcion = new JTextField();
		panel_7.add(txtDescripcion);
		txtDescripcion.setColumns(60);
		
		JPanel panel_8 = new JPanel();
		contentPane.add(panel_8, BorderLayout.SOUTH);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_8.add(btnGuardar);
		
		btnBuscarCheque.setActionCommand("buscar cheque");
		
		txtIdCheque = new JTextField();
		txtIdCheque.setVisible(false);
		panel_9.add(txtIdCheque, BorderLayout.WEST);
		//txtIdCheque.setColumns();
		btnGuardar.setActionCommand("guardar en bd");
		
	}
	
	public void setControlador(ControladorVentanaIngresarDeposito controlador) {
		btnBuscarCheque.addActionListener(controlador);
		btnGuardar.addActionListener(controlador);
		
		txtEfectivo.addKeyListener(controlador);
		
	}
}
