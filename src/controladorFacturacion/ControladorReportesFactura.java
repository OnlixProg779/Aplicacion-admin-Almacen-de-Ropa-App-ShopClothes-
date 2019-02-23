package controladorFacturacion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import conexion.Conexion;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.Cliente;
import modeloEntidades.VistaDetallefactura;
import modeloEntidades.VistaFactura;

public class ControladorReportesFactura {

	private static ArbolAVL<VistaDetallefactura> detalleFactura;
	private static VistaFactura vistaFactura;

	public static List<VistaDetallefactura> cargarItemsFacturados() {

		return detalleFactura.inOrdenList();
	}
	
	public static ArbolAVL<VistaDetallefactura> cargarItemsFacturadosArbol() {

		return detalleFactura;
	}

	public static VistaFactura cargarFactura() {

		return vistaFactura;

	}

	public static void buscarFactura(String numeroFactura) {
		ControladorFacturarDAO con = new ControladorFacturarDAO();

		try {
			detalleFactura = con.listarDetalleFactura(numeroFactura);

			vistaFactura = con.getFactura(numeroFactura);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
