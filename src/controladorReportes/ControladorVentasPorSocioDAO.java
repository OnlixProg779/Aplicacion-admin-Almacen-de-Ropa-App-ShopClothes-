package controladorReportes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;

import modeloEntidades.VistaVentasSocio;

public class ControladorVentasPorSocioDAO {

	public List<VistaVentasSocio> listarVentasDeSocio(int idSocio, java.sql.Date fechaDesde, java.sql.Date fechaHasta)
			throws SQLException {
		Conexion con = new Conexion();
		Connection connection = con.ObtenerConexion();
		List<VistaVentasSocio> listaDetallefactuara = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery(

					"select detallefactura.numerofactura, bodega.nombre as producto, detallefactura.cantidad, detallefactura.preciounitario, detallefactura.preciototal, factura.fecha, bodega.costo, (bodega.costo * detallefactura.cantidad) as costo_tot from detallefactura  natural join factura inner join bodega on detallefactura.idproducto = bodega.idproducto  where detallefactura.idproducto in  (select idProducto from bodega where idsocio = "
							+ idSocio + ") and fecha between '" + fechaDesde + "' and '" + fechaHasta
							+ "' order by numerofactura"

			);// susceptible de sql injection
			while (resultado.next()) {

				VistaVentasSocio oe = new VistaVentasSocio();

				oe.setNumFactura(resultado.getString(1));
				oe.setProducto(resultado.getString(2));
				oe.setCantidad(resultado.getInt(3));
				oe.setPrecioUnit(resultado.getDouble(4));
				oe.setPrecioTot(resultado.getDouble(5));
				oe.setFecha(resultado.getDate(6));
				oe.setCosto(resultado.getDouble(7));
				oe.setCostotot(resultado.getDouble(8));

				listaDetallefactuara.add(oe);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaDetallefactuara;

	}
	
	public String getNombreSocio(int idsocio) {
		Conexion con = new Conexion();

    	try {
			Connection connection = con.ObtenerConexion();
			Statement statement = connection.createStatement();																
			
			ResultSet resultado = statement
					.executeQuery("select nombre from socios where idsocio =  "+ idsocio );// susceptible de sql
																									// injection
			while (resultado.next()) {
				return resultado.getString(1);					
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}	    	
    	return "";
    }


}
