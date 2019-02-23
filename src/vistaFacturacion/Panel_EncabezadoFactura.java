package vistaFacturacion;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

import vista.JpanelFondo;

import javax.swing.ImageIcon;

public class Panel_EncabezadoFactura extends JPanel {
	public JLabel lblDireccion;
	public JLabel lblMail;
	public JLabel lblTelefono;
	public JLabel lblEmpresa;
	public JLabel lblPropietario;
	public JLabel lblSerie;
	public JLabel lblFechaActual;
	public JLabel lblMachala;
	public JLabel lblAutorizacionSRI;
	public JLabel lblSlogan;
	public JLabel lblRUC;

	/**
	 * Create the panel.
	 */
	public Panel_EncabezadoFactura() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(15, 0));
		
	JpanelFondo panel_LogoImagen = new JpanelFondo("");
		panel.add(panel_LogoImagen, BorderLayout.WEST);
		
		JLabel label = new JLabel("        ");
		panel_LogoImagen.add(label, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 3, 20, 20));
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_4.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblEmpresa = new JLabel("Almacen Pelileo");
		lblEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblEmpresa);
		lblEmpresa.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		
		lblSlogan = new JLabel("\"Ropa  y Accesorios de calidad\"");
		lblSlogan.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblSlogan);
		lblSlogan.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		
		lblPropietario = new JLabel("Pelileo Ciudad Azul -Pasaje");
		lblPropietario.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblPropietario);
		lblPropietario.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		
		JPanel panel_2 = new JPanel();
		panel_4.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 3, 3));
		
		JLabel lblDireccion_1 = new JLabel("Direccion:  ");
		lblDireccion_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblDireccion_1.setFont(new Font("Dialog", Font.BOLD, 9));
		panel_2.add(lblDireccion_1);
		
		lblDireccion = new JLabel("<html>Eloy Alfaro / Juan Montalvo y Municipalidad (Pasaje \u2013 El Oro)<html>");
		lblDireccion.setFont(new Font("Dialog", Font.PLAIN, 9));
		panel_2.add(lblDireccion);
		
		JLabel lblMail_1 = new JLabel("Mail:  ");
		lblMail_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblMail_1.setFont(new Font("Dialog", Font.BOLD, 9));
		panel_2.add(lblMail_1);
		
		lblMail = new JLabel("<html>juancabrera-70@hotmail.es<html>");
		lblMail.setFont(new Font("Dialog", Font.PLAIN, 9));
		panel_2.add(lblMail);
		
		JLabel lblTelefonos = new JLabel("Telefonos:  ");
		lblTelefonos.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefonos.setFont(new Font("Dialog", Font.BOLD, 9));
		panel_2.add(lblTelefonos);
		
		lblTelefono = new JLabel("<html>0985201567 - 0992621881<html>");
		lblTelefono.setFont(new Font("Dialog", Font.PLAIN, 9));
		panel_2.add(lblTelefono);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_7.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label_3 = new JLabel("RUC: ");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_6.add(label_3);
		
		lblRUC = new JLabel("1700276586");
		lblRUC.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblRUC.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblRUC);
		
		JPanel panel_5 = new JPanel();
		panel_7.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label_4 = new JLabel("AUTORIZACION SRI");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(label_4);
		
		lblAutorizacionSRI = new JLabel("1899273519");
		lblAutorizacionSRI.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAutorizacionSRI.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblAutorizacionSRI);
		
		JPanel panel_11 = new JPanel();
		panel_1.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_12 = new JPanel();
		panel_11.add(panel_12, BorderLayout.NORTH);
		
		JLabel lblFactura_1 = new JLabel("FACTURA");
		lblFactura_1.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		panel_12.add(lblFactura_1);
		
		JPanel panel_13 = new JPanel();
		panel_11.add(panel_13, BorderLayout.CENTER);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_13.add(panel_14, BorderLayout.NORTH);
		
		lblSerie = new JLabel("SERIE 001-001-");
		lblSerie.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		panel_14.add(lblSerie);
		
		JPanel panel_15 = new JPanel();
		panel_13.add(panel_15, BorderLayout.CENTER);
		panel_15.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblFecha = new JLabel("Fecha:  ");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_15.add(lblFecha);
		
		lblFechaActual = new JLabel("yyyy-MM-dd");
		panel_15.add(lblFechaActual);
		
		JLabel lblLugar = new JLabel("Lugar:  ");
		lblLugar.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_15.add(lblLugar);
		
		lblMachala = new JLabel("Pasaje");
		panel_15.add(lblMachala);

	}
}
