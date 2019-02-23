package vistaCuentas;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import com.toedter.calendar.JDateChooser;

public class PanelIngresoCaja_Abono extends JPanel {
	public JTextField txtValor;
	public JTextField txtDescripcion;
	public JLabel lblFechaActual;
	public JPanel panelSuperior;
	public JLabel lblNew2;
	public JLabel lblNew1;
	public JLabel lblNew3;
	public JPanel panelInferior;
	private JLabel label;
	public JDateChooser dateChooser;
	public JLabel lblFecha;

	/**
	 * Create the panel.
	 */
	public PanelIngresoCaja_Abono() {
		setLayout(new BorderLayout(0, 10));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(0, 2, 10, 10));
		
		lblFecha = new JLabel("     Fecha:");
		lblFecha.setHorizontalAlignment(SwingConstants.LEFT);
		lblFecha.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel.add(lblFecha);
		
		lblFechaActual = new JLabel("yyyy/mm/dd");
		lblFechaActual.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lblFechaActual.setText(LocalDate.now().toString());
		panel.add(lblFechaActual);
		
		JLabel lblValor = new JLabel("     Valor:");
		lblValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblValor.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel.add(lblValor);
		
		txtValor = new JTextField();
		txtValor.setColumns(10);
		panel.add(txtValor);
		
		JLabel lblDescripcion = new JLabel("     Descripcion:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescripcion.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		panel.add(txtDescripcion);
		
		panelSuperior = new JPanel();
		panelSuperior.setVisible(false);
		add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
		
		lblNew1 = new JLabel("New label");
		lblNew1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		panelSuperior.add(lblNew1);
		
		lblNew2 = new JLabel("New label");
		lblNew2.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		panelSuperior.add(lblNew2);
		
		lblNew3 = new JLabel("New label");
		lblNew3.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		panelSuperior.add(lblNew3);
		
		panelInferior = new JPanel();
		panelInferior.setVisible(false);
		add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setLayout(new GridLayout(0, 2, 10, 10));
		
		label = new JLabel("     Fecha:");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panelInferior.add(label);
		
		dateChooser = new JDateChooser();
		panelInferior.add(dateChooser);

	}

}
