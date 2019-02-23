package vistaCuentas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;

public class PanelNuevaCuentaPorPagar extends JPanel {
	public JTextField txtCuenta;
	public JTextField txtDescripcion;
	public JDateChooser dateChooserFecha;

	/**
	 * Create the panel.
	 */
	public PanelNuevaCuentaPorPagar() {
		setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblFecha = new JLabel("   Fecha:");
		lblFecha.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		add(lblFecha);
		
		dateChooserFecha = new JDateChooser();
		dateChooserFecha.setPreferredSize(new Dimension(130, 20));
		dateChooserFecha.setDateFormatString("yyyy-MM-dd");
		add(dateChooserFecha);
		
		JLabel lblCuenta = new JLabel("   Valor:");
		lblCuenta.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		add(lblCuenta);
		
		txtCuenta = new JTextField();
		add(txtCuenta);
		txtCuenta.setColumns(15);
		
		JLabel lblDescripcin = new JLabel("   Descripci\u00F3n:");
		lblDescripcin.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		add(lblDescripcin);
		
		txtDescripcion = new JTextField();
		add(txtDescripcion);
		txtDescripcion.setColumns(15);

	}
}
