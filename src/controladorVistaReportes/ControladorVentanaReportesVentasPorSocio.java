package controladorVistaReportes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import conexion.AbstractReports;
import controladorEstructuraDeDatos.ControladorListasYArboles;
import controladorFacturacion.TablasTemporales;
import controladorReportes.ControladorVentasPorSocioDAO;

import modeloEntidades.Socio;

import modeloEntidades.VistaVentasSocio;
import vista.Menu_1;

public class ControladorVentanaReportesVentasPorSocio implements ActionListener {

	private Menu_1 vista;

	public ControladorVentanaReportesVentasPorSocio(Menu_1 vista) {
		super();
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getActionCommand().equals("Generar")) {

			generar();

		} else if (e.getActionCommand().equals("Imprimir")) {

			imprimirReporte();

		}
	}

	private void imprimirReporte() {

		conexion.Conexion con = new conexion.Conexion();
		ControladorVentasPorSocioDAO contventas = new ControladorVentasPorSocioDAO();

		try (Connection connection = con.ObtenerConexion()) {
			Map<String, Object> parameters = new HashMap<>();

			System.out.println(idSocio + " " + contventas.getNombreSocio(idSocio));
			parameters.put("idSocio", (Integer) idSocio);
			parameters.put("nomsocio", (String) contventas.getNombreSocio(idSocio));
			parameters.put("fechaDesde", (java.sql.Date) fechaDesde);
			parameters.put("fechaHasta", (java.sql.Date) fechaHasta);
			parameters.put("ventas", (Double) ventas);
			parameters.put("compras", (Double) compras);
			parameters.put("utilidad", (Double) utilidad);

			AbstractReports.crearReporte(connection, "src\\ReportesAlmacen\\VentasPorSocio.jasper", parameters);
			AbstractReports.showViewer();
			// limpiarFactura();

		} catch (SQLException ex) {

			ex.printStackTrace();
			ex.getMessage();

		}

	}

	private double ventas;
	private double compras;
	private double utilidad;

	private Integer idSocio;
	private java.sql.Date fechaDesde;
	private java.sql.Date fechaHasta;

	private void generar() {
		ventas = 0;
		compras = 0;
		utilidad = 0;

			try {
		this.idSocio = ((Socio) vista.internalFrameReportesVentasPorSocios.getPanel().comboBox.getSelectedItem())
				.getIdsocio();
		this.fechaDesde = java.sql.Date.valueOf(TablasTemporales
				.asLocalDate(vista.internalFrameReportesVentasPorSocios.getPanel().dateChooserDesde.getDate()));
		this.fechaHasta = java.sql.Date.valueOf(TablasTemporales
				.asLocalDate(vista.internalFrameReportesVentasPorSocios.getPanel().dateChooserHasta.getDate()));
			}catch(NullPointerException ec) {
				JOptionPane.showMessageDialog(null, "Ingrese valores");
				return;
			}
		
	
		
		ControladorListasYArboles.actualizarVentasDeSocio(this.idSocio, this.fechaDesde, this.fechaHasta);

		actualizarTabla();

		ventas = ventasBrutas();
		compras = costoTotal();
		utilidad = ventas - compras;

		vista.internalFrameReportesVentasPorSocios.getPanel().labelVentas.setText(String.valueOf(ventas));
		vista.internalFrameReportesVentasPorSocios.getPanel().lblCosto.setText(String.valueOf(compras));
		vista.internalFrameReportesVentasPorSocios.getPanel().lblUtilidadTot.setText(String.valueOf(utilidad));
		}
	/**else {
			JOptionPane.showMessageDialog(null, "Llene todos los campos","*** AVISO ***",JOptionPane.INFORMATION_MESSAGE);
		}

	}*/

	public void actualizarTabla() {
		DefaultTableModel mod;
		mod = mostrarTablaSocioVenta(ControladorListasYArboles.ListaVistaVentasSocio);

		vista.internalFrameReportesVentasPorSocios.getPanel().table.setModel(mod);

	}

	private DefaultTableModel mostrarTablaSocioVenta(List<VistaVentasSocio> lista) {
		String[] titulos = { "# Fact", "Cant", "Producto", "Precio", "Total", "Costo", "Costo total" };
		String[] registro = new String[7];
		DefaultTableModel modelo = new DefaultTableModel(null, titulos);
		for (VistaVentasSocio oe : lista) {
			registro[0] = oe.getNumFactura();
			registro[1] = String.valueOf(oe.getCantidad());
			registro[2] = oe.getProducto();
			registro[3] = String.valueOf(oe.getPrecioUnit());
			registro[4] = String.valueOf(oe.getPrecioTot());
			registro[5] = String.valueOf(oe.getCosto());
			registro[6] = String.valueOf(oe.getCostotot());

			modelo.addRow(registro);
			// modelo.setColumnIdentifiers(titulos);

		}
		return modelo;
	}

	public DefaultComboBoxModel<Socio> actualizarItemsSocios() {

		DefaultComboBoxModel<Socio> model = new DefaultComboBoxModel<Socio>();

		for (int i = 0; i < ControladorListasYArboles.arbolSocios.size(); i++) {
			if (i == 0) {
				model.addElement(new Socio("<<Seleccione>>", "", ""));
			}
			model.addElement(ControladorListasYArboles.arbolSocios.getPreOrden(i));

		}
		return model;

	}

	private double ventasBrutas() {
		double x = 0;
		for (int i = 0; i < ControladorListasYArboles.ListaVistaVentasSocio.size(); i++) {
			x = x + ControladorListasYArboles.ListaVistaVentasSocio.get(i).getPrecioTot();
		}
		return x;
	}

	private double costoTotal() {
		double x = 0;
		for (int i = 0; i < ControladorListasYArboles.ListaVistaVentasSocio.size(); i++) {
			x = x + ControladorListasYArboles.ListaVistaVentasSocio.get(i).getCostotot();
		}
		return x;
	}

}
