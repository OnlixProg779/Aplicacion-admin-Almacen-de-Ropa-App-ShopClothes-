package controladorBodega;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import conexion.Conexion;
import controladorFacturacion.TablasTemporales;
import modeloEntidades.Notaentrega;
import modeloEntidades.NotaentregaBodega;

public class ControladorNotaDeEntregaDAO {

	
	private final String INSERT_BODEGA = "INSERT into notaentrega_bodega values (?,?,?,?)";
	
	
	public boolean insetarNotaEntrega(Notaentrega nota) {

		Conexion con = new Conexion();
		boolean estado = false;
		try (Connection connection = con.ObtenerConexion()) {
			PreparedStatement pst = connection.prepareStatement("insert into notaentrega values (?,?,?,?,?)");

			pst.setInt(1, nota.getIdnotadeeentrega());
			pst.setInt(2, nota.getIdsocio());
			pst.setString(3, nota.getNumeroNotaEntrega());
			pst.setDate(4, Date.valueOf(TablasTemporales.asLocalDate(nota.getFecha())));
			pst.setString(5, nota.getReceptor());

			int n = pst.executeUpdate();
			if (n != 0) {
				estado = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return estado;
	}

	public int num_NotaEntrega() throws SQLException {
		Conexion con = new Conexion();
		Connection connection = con.ObtenerConexion();
		int x = 0;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery("select max(idnotaentrega) from notaentrega_bodega");// susceptible
																												// de
																												// sql
																												// injection
			while (resultado.next()) {

				x = (resultado.getInt(1)) + 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;

	}
	
	public boolean insetarDetalleNotaDeEntrega(List<NotaentregaBodega> lista, int idNotaEntrega) {

		conexion.Conexion con = new conexion.Conexion();
		boolean estado = false;

		try (Connection connection = con.ObtenerConexion()) {
			PreparedStatement pst = connection.prepareStatement(INSERT_BODEGA);
			for (NotaentregaBodega detallefactura : lista) {
				try {
					pst.setInt(1, idNotaEntrega);
					pst.setInt(2, detallefactura.getIdproducto());
					pst.setInt(3, detallefactura.getCantidad());
					pst.setDouble(4, detallefactura.getCosto());

					int n = pst.executeUpdate();
					if (n != 0) {
						estado = true;
					}
				} catch (SQLException ex) {
					System.out.println(ex.getMessage() + " Fallo en el ingreso de medico a la base de datos" + "\n"
							+ ex.getStackTrace() + "\n" + ex.getSQLState());
					ex.getMessage();
					continue;
				}

			}

			connection.close();
			pst.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage() + " Fallo en el ingreso de medico a la base de datos" + "\n"
					+ e.getStackTrace() + "\n" + e.getSQLState());
			e.getMessage();

		}

		return estado;
	}
}
