package vistaSocios;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladorVistaFacturacion.ControladorVentanaNuevaFactura;
import controladorVistaSocios.ControladorVentanaSociosNuevoSocio;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;

public class JFrameSociosNuevoSocio extends JFrame {

	private JPanel contentPane;
	public JTextField txtRuc;
	public JTextField txtNombre;
	public JTextField txtReferencia;
	private JButton btnGuardar;
	private JButton btnCancelar;


	
	public JFrameSociosNuevoSocio(ControladorVentanaSociosNuevoSocio controlador) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 470, 188);
		setTitle("NUEVO SOCIO");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblRuc = new JLabel("RUC:");
		lblRuc.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel.add(lblRuc);
		
		txtRuc = new JTextField();
		panel.add(txtRuc);
		txtRuc.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblReferencia = new JLabel("Referencia:");
		lblReferencia.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel.add(lblReferencia);
		
		txtReferencia = new JTextField();
		panel.add(txtReferencia);
		txtReferencia.setColumns(10);
		
		btnGuardar = new JButton("Guardar");
		panel.add(btnGuardar);
				
		btnCancelar = new JButton("Cancelar");
		panel.add(btnCancelar);
		
		btnGuardar.setActionCommand("Guardar Socio");
		btnCancelar.setActionCommand("Cancelar");
	
		setControlador(controlador);
	}
	
	public void setControlador(ControladorVentanaSociosNuevoSocio controlador) {
		btnGuardar.addActionListener(controlador);
		btnCancelar.addActionListener(controlador);
	}
}