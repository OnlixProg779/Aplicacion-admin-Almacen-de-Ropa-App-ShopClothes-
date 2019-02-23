package controladorSocios;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;

import conexion.Conexion;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.Socio;
import modeloEntidades.Telefono;

public class ControladorSociosVerSociosDAO {

	private static ArbolAVL<Socio> arbolAVLSocios;

	private static final String LISTAR = "Select * from socios";
	private static final String LISTAR_TELEFONOS = "Select * from telefonos";

	public ArbolAVL<Socio> listarSociosEnArbol() throws SQLException {
		Conexion con = new Conexion();
		Connection connection = con.ObtenerConexion();
		arbolAVLSocios = new ArbolAVL<Socio>("Arbol de Socios");
		try {
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery(LISTAR);// susceptible de sql injection
			while (resultado.next()) {
				Socio oe = new Socio();

				oe.setIdsocio(resultado.getInt(1));
				oe.setRuc(resultado.getString(2));
				oe.setNombre(resultado.getString(3));
				oe.setReferencia(resultado.getString(4));
				oe.setTelefonos(listarTelefonos(oe.getIdsocio()));

				arbolAVLSocios.add(oe);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arbolAVLSocios;

	}

	private List<Telefono> listarTelefonos(int idSocio) throws SQLException {
		Conexion con = new Conexion();
		Connection connection = con.ObtenerConexion();
		List<Telefono> listaTelefonos = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery(LISTAR_TELEFONOS + " where idsocio = " + idSocio);// susceptible
																											// de sql
																											// injection
			while (resultado.next()) {
				Telefono oe = new Telefono();

				oe.setIdsocio(resultado.getInt(1));
				oe.setTelefono(resultado.getString(2));
				;
				oe.setReferencia(resultado.getString(3));

				listaTelefonos.add(oe);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaTelefonos;

	}

	
	public DefaultListModel<String> jListarNumeros(int idSocio) {

		DefaultListModel<String> listModel = new DefaultListModel<>();
		List<Telefono> telefonos = null;
		Socio socio = new Socio(idSocio);
		if (arbolAVLSocios.getRaiz() != null) {
			telefonos = arbolAVLSocios.getNodo(socio).getDato().getTelefonos();
			for (int i = 0; i < telefonos.size(); i++) {

				listModel.add(i, telefonos.get(i).getReferencia() + " - " + telefonos.get(i).getTelefono());
			}

		}

		return listModel;
	}

	public ArbolAVL<Socio> filtrarSocioNombre(String buscar) {
		Conexion con = new Conexion();

		ArbolAVL<Socio> listaSocios = new ArbolAVL<>();
		try {
			Connection connection = con.ObtenerConexion();
			Statement statement = connection.createStatement();// "Nombre like \""+txtBsucar.getText()+"%\" or Apellido
																// like \""+txtBsucar.getText()+"%\"";
			String filtro = buscar + "_%";
			// select * from socios where nombre like 'D_%';
			ResultSet resultado = statement
					.executeQuery("select * from socios where nombre like " + "'" + filtro + "'");// susceptible de sql
																									// injection
			while (resultado.next()) {
				Socio oe = new Socio();

				oe.setIdsocio(resultado.getInt(1));
				oe.setRuc(resultado.getString(2));
				oe.setNombre(resultado.getString(3));
				oe.setReferencia(resultado.getString(4));
				oe.setTelefonos(listarTelefonos(oe.getIdsocio()));

				listaSocios.add(oe);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaSocios;

	}

}
