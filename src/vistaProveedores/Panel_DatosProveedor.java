package vistaProveedores;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class Panel_DatosProveedor extends JPanel {
	public JTextField txtRuc;
	public JTextField txtNombre;
	public JTextField txtweb;
	public JTextField txtFechaRegistro;
	private JLabel label_4;
	private JTextField textField_4;
	private JLabel label_5;
	private JTextField textField_5;

	/**
	 * Create the panel.
	 */
	public Panel_DatosProveedor() {
		setLayout(new GridLayout(0, 2, 5, 5));
		
		JLabel label = new JLabel("Ruc/CI:");
		label.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		add(label);
		
		txtRuc = new JTextField();
		txtRuc.setColumns(10);
		add(txtRuc);
		
		JLabel label_1 = new JLabel("Nombre:");
		label_1.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		add(label_1);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		add(txtNombre);
		
		JLabel label_2 = new JLabel("Pagina Web:");
		label_2.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		add(label_2);
		
		txtweb = new JTextField();
		txtweb.setColumns(10);
		add(txtweb);
		
		JLabel label_3 = new JLabel("Fecha Registro:");
		label_3.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		add(label_3);
		
		txtFechaRegistro = new JTextField();
		txtFechaRegistro.setColumns(10);
		add(txtFechaRegistro);
		
		label_4 = new JLabel("Fecha Registro:");
		label_4.setEnabled(false);
		label_4.setVisible(false);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setEnabled(false);
		textField_4.setVisible(false);
		textField_4.setColumns(10);
		add(textField_4);
		
		label_5 = new JLabel("Fecha Registro:");
		label_5.setEnabled(false);
		label_5.setVisible(false);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(label_5);
		
		textField_5 = new JTextField();
		textField_5.setEnabled(false);
		textField_5.setVisible(false);
		textField_5.setColumns(10);
		add(textField_5);

	}

}
