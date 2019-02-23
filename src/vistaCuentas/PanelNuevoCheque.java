package vistaCuentas;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import com.toedter.components.JLocaleChooser;
import com.toedter.calendar.JDateChooser;

public class PanelNuevoCheque extends JPanel {
	public JTextField txtNumCheque;
	public JTextField txtChequeBanco;
	public JTextField txtTitularCuenta;
	public JTextField txtValor;
	public JLabel lbllNfact;
	public JLabel lblNumeroDeFactura;
	public JDateChooser dateChooserIngreso;
	public JDateChooser dateChooserDeposito;

	/**
	 * Create the panel.
	 */
	public PanelNuevoCheque() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel lblNCheque = new JLabel("N\u00BA Cheque:");
		lblNCheque.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel.add(lblNCheque);
		
		txtNumCheque = new JTextField();
		panel.add(txtNumCheque);
		txtNumCheque.setColumns(40);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblChequeBanco = new JLabel("Cheque Banco:");
		lblChequeBanco.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_1.add(lblChequeBanco);
		
		txtChequeBanco = new JTextField();
		panel_1.add(txtChequeBanco);
		txtChequeBanco.setColumns(10);
		
		JLabel lblTitularCuenta = new JLabel("Titular Cuenta:");
		lblTitularCuenta.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_1.add(lblTitularCuenta);
		
		txtTitularCuenta = new JTextField();
		panel_1.add(txtTitularCuenta);
		txtTitularCuenta.setColumns(10);
		
		JLabel lblFechaIngreso = new JLabel("Fecha Ingreso:");
		lblFechaIngreso.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_1.add(lblFechaIngreso);
		
		dateChooserIngreso = new JDateChooser();
		dateChooserIngreso.setDateFormatString("yyyy-MM-dd");
		panel_1.add(dateChooserIngreso);
		
		JLabel lblFechaDepsito = new JLabel("Fecha Dep\u00F3sito:");
		lblFechaDepsito.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_1.add(lblFechaDepsito);
		
		dateChooserDeposito = new JDateChooser();
		dateChooserDeposito.setDateFormatString("yyyy-MM-dd");
		panel_1.add(dateChooserDeposito);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_1.add(lblValor);
		
		txtValor = new JTextField();
		panel_1.add(txtValor);
		txtValor.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);
		
		lbllNfact = new JLabel("***");
		panel_2.add(lbllNfact);
		
		lblNumeroDeFactura = new JLabel("***");
		panel_2.add(lblNumeroDeFactura);

	}

}
