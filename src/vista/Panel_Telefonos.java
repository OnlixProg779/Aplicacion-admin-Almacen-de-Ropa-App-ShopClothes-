package vista;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Panel_Telefonos extends JPanel {
	private JTextField txtTelefonoN;
	private JTextField txtReferenciaN;

	/**
	 * Create the panel.
	 */
	public Panel_Telefonos() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Telefono:    ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblNewLabel);
		
		txtTelefonoN = new JTextField();
		add(txtTelefonoN);
		txtTelefonoN.setColumns(15);
		
		JLabel lblNewLabel_1 = new JLabel("Referencia:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblNewLabel_1);
		
		txtReferenciaN = new JTextField();
		add(txtReferenciaN);
		txtReferenciaN.setColumns(15);

	}

}
