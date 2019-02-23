package vistaFacturacion;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import controladorVistaFacturacion.ControladorVentanaNuevaFactura;

import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import vista.Panel_AddPrductosATabla;

import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.Color;

public class Panel_TablaFacturaVentas extends JPanel {
	public JTextField txtSubTotal;
	public JTextField txtTotal;
	public JTextField txtIva;
	private Panel_AddPrductosATabla panelProductosTabla;
	public JTable tableFactura;

	
	public Panel_AddPrductosATabla getPanelProductosTabla() {
		return panelProductosTabla;
	}

	/**
	 * Create the panel.
	 */
	public Panel_TablaFacturaVentas() {
		setLayout(new BorderLayout(10, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		tableFactura = new JTable();
		scrollPane.setViewportView(tableFactura);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(10, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.EAST);
		
		txtSubTotal = new JTextField();
		txtSubTotal.setDisabledTextColor(Color.BLACK);
		txtSubTotal.setEnabled(false);
		txtSubTotal.setBorder(null);
		txtSubTotal.setColumns(10);
		
		txtTotal = new JTextField();
		txtTotal.setDisabledTextColor(Color.BLACK);
		txtTotal.setEnabled(false);
		txtTotal.setBorder(null);
		txtTotal.setColumns(10);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		panel_4.add(txtSubTotal);
		
		txtIva = new JTextField();
		txtIva.setDisabledTextColor(Color.BLACK);
		txtIva.setEnabled(false);
		txtIva.setBorder(null);
		panel_4.add(txtIva);
		txtIva.setColumns(10);
		panel_4.add(txtTotal);
		
		JPanel panel_7 = new JPanel();
		panel_3.add(panel_7, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Sub Total:  ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		
		JLabel lblNewLabel_1 = new JLabel("Total:  ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		panel_7.setLayout(new GridLayout(0, 1, 0, 0));
		panel_7.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Iva 12%:  ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		panel_7.add(lblNewLabel_2);
		panel_7.add(lblNewLabel_1);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6, BorderLayout.EAST);
		panel_6.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNuevoCliente_1 = new JButton("Nuevo Cliente");
		btnNuevoCliente_1.setEnabled(false);
		btnNuevoCliente_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_6.add(btnNuevoCliente_1);
		btnNuevoCliente_1.setVisible(false);
		
		JPanel panel_5 = new JPanel();
		add(panel_5, BorderLayout.EAST);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNuevoCliente = new JButton("Nuevo Cliente");
		btnNuevoCliente.setEnabled(false);
		btnNuevoCliente.setVisible(false);
		btnNuevoCliente.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_5.add(btnNuevoCliente);
		
		panelProductosTabla = new Panel_AddPrductosATabla();
		panelProductosTabla.setPreferredSize(new Dimension(350, 30));
		add(panelProductosTabla, BorderLayout.WEST);

	}

	public void setControlador(ControladorVentanaNuevaFactura controlador) {

		panelProductosTabla.setControlador(controlador);
	}

}
