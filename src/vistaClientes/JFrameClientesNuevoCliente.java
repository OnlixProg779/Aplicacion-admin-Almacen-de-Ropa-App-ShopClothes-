package vistaClientes;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladorVistaClientes.ControladorVentanaClientesNuevoCliente;


import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;



public class JFrameClientesNuevoCliente extends JFrame {


		private JPanel contentPane;
		
		public JTextField txtRuc;
		public JTextField txtNombre;
		public JTextField txtApellido;
		public JTextField txtDireccion;
		public JTextField txtTelefono;
		private JButton btnAgregar;
		private JButton btnCancelar;

		public JFrameClientesNuevoCliente(ControladorVentanaClientesNuevoCliente controlador) {
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 438, 260);
			setTitle("NUEVO CLIENTE");
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(0, 2, 10, 10));
			
			JLabel lblNewLabel = new JLabel("RUC:");
			lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			panel.add(lblNewLabel);
			
			txtRuc = new JTextField();
			panel.add(txtRuc);
			txtRuc.setColumns(10);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			panel.add(lblNombre);
			
			txtNombre = new JTextField();
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			
			JLabel lblApellido = new JLabel("Apellido:");
			lblApellido.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			panel.add(lblApellido);
			
			txtApellido = new JTextField();
			panel.add(txtApellido);
			txtApellido.setColumns(10);
			
			JLabel lblDireccion = new JLabel("Direccion:");
			lblDireccion.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			panel.add(lblDireccion);
			
			txtDireccion = new JTextField();
			panel.add(txtDireccion);
			txtDireccion.setColumns(10);
			
			JLabel lblTelefono = new JLabel("Telefono:");
			lblTelefono.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			panel.add(lblTelefono);
			
			txtTelefono = new JTextField();
			panel.add(txtTelefono);
			txtTelefono.setColumns(10);
			
			btnAgregar = new JButton("Guardar");
			panel.add(btnAgregar);
			
			btnCancelar = new JButton("Cancelar");
			panel.add(btnCancelar);
			
			btnAgregar.setActionCommand("Guardar Cliente");
			btnCancelar.setActionCommand("Cancelar");
			
			setControlador(controlador);
		}
		

	
	public void setControlador(ControladorVentanaClientesNuevoCliente controlador) {
		btnAgregar.addActionListener(controlador);
		btnCancelar.addActionListener(controlador);
	}

}