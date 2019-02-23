package vistaBodega;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladorVistaBodega.ControladorVentanaBodegaNuevoProducto;
import controladorVistaSocios.ControladorVentanaSociosNuevoSocio;
import modeloEntidades.Categoria;
import modeloEntidades.Socio;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.GridLayout;

import controladorVistaBodega.ControladorVentanaBodegaNuevoProducto;

public class JFrameBodegaNuevoProducto extends JFrame {

	private JPanel contentPane;
	public JTextField txtNombre;
	public JTextField txtDescripcion;
	private JButton btnGuardar;
	private JButton btnNewButton;
	public JComboBox<Categoria> comboBoxCategoria;
	public Socio socio;



	/**
	 * Create the frame.
	 */
	public JFrameBodegaNuevoProducto(ControladorVentanaBodegaNuevoProducto controlador, Socio socio) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 552, 185);
		setTitle("NUEVO PRODUCTO");
		contentPane = new JPanel();
		this.socio = socio;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblCategora = new JLabel("Categor\u00EDa:");
		lblCategora.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel.add(lblCategora);
		
		comboBoxCategoria = new JComboBox();
		panel.add(comboBoxCategoria);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDescrpcion = new JLabel("Descripcion:");
		lblDescrpcion.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel.add(lblDescrpcion);
		
		txtDescripcion = new JTextField();
		panel.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel.add(btnGuardar);
		
		btnNewButton = new JButton("Cancelar");
		btnNewButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel.add(btnNewButton);
		
		btnGuardar.setActionCommand("Guardar Producto");
		btnNewButton.setActionCommand("Cancelar");
		
		setControlador(controlador);
	}
	public void setControlador(ControladorVentanaBodegaNuevoProducto controlador) {
		btnGuardar.addActionListener(controlador);
		btnNewButton.addActionListener(controlador);
		comboBoxCategoria.addItemListener(controlador);
	}

}
