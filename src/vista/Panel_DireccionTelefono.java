package vista;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import modeloEntidades.Telefono;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.border.TitledBorder;

import controladorVistaPrincipal.ControladorVentanaProveedores;

import javax.swing.ImageIcon;

public class Panel_DireccionTelefono extends JPanel {
	private JPanel panel;
	private JPanel panel_1;
	public JTextField txtReferencia;
	public JTextField txtCalleSecundaria;
	public JTextField txtNumCasa;
	public JTextField txtCallePrincipal;
	public JTextField txtCiudad;
	private JButton btnNuevoTelefono;

	/**
	 * Create the panel.
	 */
	public Panel_DireccionTelefono() {
		setLayout(new BorderLayout(10, 0));
		
		panel = new JPanel();
		add(panel, BorderLayout.EAST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JList<Telefono> list = new JList<Telefono>();
		list.setBorder(new TitledBorder(null, "Tel\u00E9fonos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Telefonos", "1", "2", "3", "4", "5"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		panel.add(list, BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 5, 5));
		
		JLabel label = new JLabel("Ciudad:");
		label.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_1.add(label);
		
		txtCiudad = new JTextField();
		txtCiudad.setColumns(10);
		panel_1.add(txtCiudad);
		
		JLabel label_1 = new JLabel("Calle Principal:");
		label_1.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_1.add(label_1);
		
		txtCallePrincipal = new JTextField();
		txtCallePrincipal.setColumns(10);
		panel_1.add(txtCallePrincipal);
		
		JLabel label_2 = new JLabel("N\u00B0 Casa");
		label_2.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_1.add(label_2);
		
		txtNumCasa = new JTextField();
		txtNumCasa.setColumns(10);
		panel_1.add(txtNumCasa);
		
		JLabel label_3 = new JLabel("Calle Secundaria:");
		label_3.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_1.add(label_3);
		
		txtCalleSecundaria = new JTextField();
		txtCalleSecundaria.setColumns(10);
		panel_1.add(txtCalleSecundaria);
		
		JLabel label_4 = new JLabel("Referencia:");
		label_4.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_1.add(label_4);
		
		txtReferencia = new JTextField();
		txtReferencia.setColumns(10);
		panel_1.add(txtReferencia);
		
		JLabel label_5 = new JLabel("Tel\u00E9fono:");
		label_5.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_1.add(label_5);
		
		btnNuevoTelefono = new JButton("Agregar Nuevo");
		btnNuevoTelefono.setIcon(new ImageIcon(Panel_DireccionTelefono.class.getResource("/Imagenes/phone-add_16x16.png")));
		btnNuevoTelefono.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		btnNuevoTelefono.setActionCommand("Telefono Nuevo");
		panel_1.add(btnNuevoTelefono);

	}
	
	public <T> void setControlador(T controlador) {
		btnNuevoTelefono.addActionListener((ActionListener)controlador);

	}
}
