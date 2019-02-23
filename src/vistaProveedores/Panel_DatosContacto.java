package vistaProveedores;

import javax.swing.JPanel;
import javax.swing.JTextField;

import modeloEntidades.Telefono;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.border.TitledBorder;

import controladorVistaPrincipal.ControladorVentanaProveedores;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import java.awt.Dimension;

public class Panel_DatosContacto extends JPanel {
	private JPanel panel;
	private JButton btnNuevoTelefono;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JTextField txtCedula;
	private JTextField txtContacto;
	private JTextField txtEmail;
	private JPanel panel_1;
	private JList<Telefono> list;
	private JButton btnEliminar;
	private JPanel panel_2;
	private JButton btnModificar;
	private JPanel panel_3;
	private JButton btnNuevoContacto;
	private JComboBox comboBox;

	/**
	 * Create the panel.
	 */
	public Panel_DatosContacto() {
		setLayout(new BorderLayout(0, 5));
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 5, 5));
		
		label_3 = new JLabel("Cedula:");
		label_3.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel.add(label_3);
		
		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		panel.add(txtCedula);
		
		label_2 = new JLabel("Contacto:");
		label_2.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel.add(label_2);
		
		txtContacto = new JTextField();
		txtContacto.setColumns(10);
		panel.add(txtContacto);
		
		label_1 = new JLabel("Correo Electronico:");
		label_1.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel.add(label_1);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		panel.add(txtEmail);
		
		label = new JLabel("Telefono:");
		label.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel.add(label);
		
		btnNuevoTelefono = new JButton("Agregar Nuevo");
		btnNuevoTelefono.setIcon(new ImageIcon(Panel_DatosContacto.class.getResource("/Imagenes/phone-add_16x16.png")));
		btnNuevoTelefono.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		btnNuevoTelefono.setActionCommand("Nuevo Telefono contacto");
		panel.add(btnNuevoTelefono);
		btnNuevoTelefono.setActionCommand("Nuevo Telefono contacto");
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		list = new JList<Telefono>();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Telefonos", "t", "r"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBorder(new TitledBorder(null, "Tel\u00E9fonos Contacto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(list, BorderLayout.CENTER);
		
		panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(Panel_DatosContacto.class.getResource("/Imagenes/phone-remove_16x16.png")));
		btnEliminar.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_2.add(btnEliminar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(Panel_DatosContacto.class.getResource("/Imagenes/phone-info(edit)_16x16.png")));
		btnModificar.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_2.add(btnModificar);
		
		btnEliminar.setActionCommand("Eliminar Numero De Contacto");
		btnModificar.setActionCommand("Modificar Numero De Contacto");
		
		panel_3 = new JPanel();
		add(panel_3, BorderLayout.NORTH);
		
		comboBox = new JComboBox();
		comboBox.setPreferredSize(new Dimension(120, 20));
		panel_3.add(comboBox);
		
		btnNuevoContacto = new JButton("Add Nuevo");
		btnNuevoContacto.setIcon(new ImageIcon(Panel_DatosContacto.class.getResource("/Imagenes/customer-add_16x16.png")));
		btnNuevoContacto.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnNuevoContacto.setActionCommand("Nuevo Contacto");
		panel_3.add(btnNuevoContacto);
		btnNuevoContacto.setActionCommand("Nuevo Contacto");

	}
	
	public void setControlador(ControladorVentanaProveedores controlador) {
		btnNuevoContacto.addActionListener(controlador);
		btnNuevoTelefono.addActionListener(controlador);
		btnEliminar.addActionListener(controlador);
		btnModificar.addActionListener(controlador);
	}
	
}
