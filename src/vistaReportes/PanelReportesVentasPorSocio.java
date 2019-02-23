package vistaReportes;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

import controladorEstructuraDeDatos.ControladorListasYArboles;
import controladorVistaReportes.ControladorVentanaReportesVentasPorSocio;
import controladorVistaReportes.ControladorVentanaReportesVerVentas;
import modeloEntidades.Socio;
import pruebas.Iniciador;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class PanelReportesVentasPorSocio extends JPanel {
	public JTable table;
	public JComboBox<Socio> comboBox;
	public JDateChooser dateChooserDesde;
	public JDateChooser dateChooserHasta;
	private JButton btnImprimir;
	private JButton btnGenerar;
	public JLabel labelVentas;
	public JLabel lblCosto;
	public JLabel lblUtilidadTot;

	/**
	 * Create the panel.
	 */
	public PanelReportesVentasPorSocio() {
		setLayout(new BorderLayout(10, 10));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_5 = new JPanel();
		scrollPane.setRowHeaderView(panel_5);
		panel_5.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new EtchedBorder(EtchedBorder.LOWERED, SystemColor.textHighlight, null));
		panel_5.add(panel_8);
		panel_8.setLayout(new GridLayout(2, 0, 0, 1));
		
		JLabel lblVentasBrutas = new JLabel("Ventas Brutas:");
		panel_8.add(lblVentasBrutas);
		lblVentasBrutas.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		
		labelVentas = new JLabel("$$");
		panel_8.add(labelVentas);
		labelVentas.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		labelVentas.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, SystemColor.textHighlight, null));
		panel_5.add(panel_7);
		panel_7.setLayout(new GridLayout(2, 0, 0, 1));
		
		JLabel lblCostoTotal = new JLabel("Costo Total:");
		panel_7.add(lblCostoTotal);
		lblCostoTotal.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		
		lblCosto = new JLabel("$$");
		panel_7.add(lblCosto);
		lblCosto.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblCosto.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, SystemColor.textHighlight, null));
		panel_5.add(panel_6);
		panel_6.setLayout(new GridLayout(2, 0, 0, 1));
		
		JLabel lblUtilidad = new JLabel("Utilidad:");
		panel_6.add(lblUtilidad);
		lblUtilidad.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		
		lblUtilidadTot = new JLabel("$$");
		panel_6.add(lblUtilidadTot);
		lblUtilidadTot.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblUtilidadTot.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 5, 5));
		
		JLabel lblSocio = new JLabel("Socio: ");
		lblSocio.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_1.add(lblSocio);
		
		comboBox = new JComboBox();

		panel_1.add(comboBox);
		
		JLabel lblDesde = new JLabel("Desde: ");
		lblDesde.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_1.add(lblDesde);
		
		dateChooserDesde = new JDateChooser();
		dateChooserDesde.setDateFormatString("yyyy-MM-dd");
		panel_1.add(dateChooserDesde);
		
		JLabel lblHasta = new JLabel("Hasta: ");
		lblHasta.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		panel_1.add(lblHasta);
		
		dateChooserHasta = new JDateChooser();
		dateChooserHasta.setDateFormatString("yyyy-MM-dd");
		panel_1.add(dateChooserHasta);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		
		btnGenerar = new JButton("Generar");
		btnGenerar.setIcon(new ImageIcon(PanelReportesVentasPorSocio.class.getResource("/Imagenes/business_report-search 32x32.png")));
		btnGenerar.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_3.add(btnGenerar);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setIcon(new ImageIcon(PanelReportesVentasPorSocio.class.getResource("/Imagenes/report-print 32x32.png")));
		btnImprimir.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_4.add(btnImprimir);
		
		btnImprimir.setActionCommand("Imprimir");
		btnGenerar.setActionCommand("Generar");
		
		
		
	}
	
	public void setControlador(ControladorVentanaReportesVentasPorSocio controlador){

		btnImprimir.addActionListener(controlador);
		btnGenerar.addActionListener(controlador);
	}

}
