package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;

public class Panel_Discapacidad extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public Panel_Discapacidad() {
		setLayout(new GridLayout(0, 2, 5, 5));
		
		JLabel lblDiscapacidad = new JLabel("Discapacidad:");
		lblDiscapacidad.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		add(lblDiscapacidad);
		
		JComboBox comboBox = new JComboBox();
		add(comboBox);
		
		JLabel lblNumeroDeCarnet = new JLabel("Numero de carnet:");
		lblNumeroDeCarnet.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		add(lblNumeroDeCarnet);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		add(lblTipo);
		
		textField_1 = new JTextField();
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPorcentaje = new JLabel("Porcentaje:");
		lblPorcentaje.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		add(lblPorcentaje);
		
		textField_2 = new JTextField();
		add(textField_2);
		textField_2.setColumns(10);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_2);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_1);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_3);

	}

}
