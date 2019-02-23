package controladorBodega;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import conexion.Conexion;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.Categoria;
import modeloEntidades.VistaBodega;

public class ControladorBodegaDAO {


	private static final String LISTAR = "Select * from vista_bodega";

	public ArbolAVL<VistaBodega> listarProductosBodega() throws SQLException {
		Conexion con = new Conexion();
		Connection connection = con.ObtenerConexion();
		ArbolAVL<VistaBodega> listaBodega = new ArbolAVL<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery(LISTAR);// susceptible de sql injection
			while (resultado.next()) {
				VistaBodega oe = new VistaBodega();

				oe.setIdproducto(resultado.getInt(1));
				oe.setCategoria(resultado.getString(2));
				oe.setNombre(resultado.getString(3));
				oe.setStock(resultado.getInt(4));
				oe.setCosto(resultado.getDouble(5));
				oe.setIdsocio(resultado.getInt(6));
				oe.setDetalle(resultado.getString(7));

				listaBodega.add(oe);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaBodega;

	}

	public List<Categoria> listarCategorias() throws SQLException {
		Conexion con = new Conexion();
		Connection connection = con.ObtenerConexion();
		List<Categoria> listaCategorias = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery("Select *  from categoria");// susceptible de sql injection
			while (resultado.next()) {
				Categoria oe = new Categoria();

				oe.setIdcategoria(resultado.getInt(1));
				oe.setCategoria(resultado.getString(2));

				listaCategorias.add(oe);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCategorias;

	}

	public ArbolAVL<VistaBodega> filtrarProducto(String buscar) {
		Conexion con = new Conexion();

		ArbolAVL<VistaBodega> listaProducto = new ArbolAVL<>();
		try {
			Connection connection = con.ObtenerConexion();
			Statement statement = connection.createStatement();// "Nombre like \""+txtBsucar.getText()+"%\" or Apellido
																// like \""+txtBsucar.getText()+"%\"";
			String filtro = buscar;

			ResultSet resultado = statement
					.executeQuery("select * from vista_bodega where nombre like " + "'" + filtro + "'");// susceptible
																										// de sql
			// injection
			while (resultado.next()) {
				VistaBodega oe = new VistaBodega();

				oe.setIdproducto(resultado.getInt(1));
				oe.setCategoria(resultado.getString(2));
				oe.setNombre(resultado.getString(3));
				oe.setStock(resultado.getInt(4));
				oe.setCosto(resultado.getDouble(5));
				oe.setIdsocio(resultado.getInt(6));
				listaProducto.add(oe);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaProducto;

	}

	public ArbolAVL<VistaBodega> filtrarProductoPorSocio(int buscar) {
		Conexion con = new Conexion();

		ArbolAVL<VistaBodega> listaProducto = new ArbolAVL<>();
		try {
			Connection connection = con.ObtenerConexion();
			Statement statement = connection.createStatement();// "Nombre like \""+txtBsucar.getText()+"%\" or Apellido
																// like \""+txtBsucar.getText()+"%\"";
			int filtro = buscar;

			ResultSet resultado = statement.executeQuery("select * from vista_bodega where idsocio = " + filtro);// susceptible
																													// de
																													// sql
																													// injection
			while (resultado.next()) {
				VistaBodega oe = new VistaBodega();

				oe.setIdproducto(resultado.getInt(1));
				oe.setCategoria(resultado.getString(2));
				oe.setNombre(resultado.getString(3));
				oe.setStock(resultado.getInt(4));
				oe.setCosto(resultado.getDouble(5));
				oe.setIdsocio(resultado.getInt(6));
				listaProducto.add(oe);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaProducto;

	}
	
	
	public ArbolAVL<VistaBodega> filtrarProductoPorSocioyCategoria(int buscar,String catego) {
		Conexion con = new Conexion();

		ArbolAVL<VistaBodega> listaProductoSocioCatego = new ArbolAVL<>();
		try {
			Connection connection = con.ObtenerConexion();
			Statement statement = connection.createStatement();// "Nombre like \""+txtBsucar.getText()+"%\" or Apellido
																// like \""+txtBsucar.getText()+"%\"";
			int filtro = buscar;

			ResultSet resultado = statement.executeQuery("select * from vista_bodega where idsocio = " + filtro +"and categoria= "+ "'" + catego +"'");
																													
			while (resultado.next()) {
				VistaBodega oe = new VistaBodega();

				oe.setIdproducto(resultado.getInt(1));
				oe.setCategoria(resultado.getString(2));
				oe.setNombre(resultado.getString(3));
				oe.setStock(resultado.getInt(4));
				oe.setCosto(resultado.getDouble(5));
				oe.setIdsocio(resultado.getInt(6));
				listaProductoSocioCatego.add(oe);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaProductoSocioCatego;

	}
	
	public ArbolAVL<VistaBodega> filtrarProductoPorCategoria(String catego) {
		Conexion con = new Conexion();

		ArbolAVL<VistaBodega> listaProductoCatego = new ArbolAVL<>();
		try {
			Connection connection = con.ObtenerConexion();
			Statement statement = connection.createStatement();
															
			ResultSet resultado = statement.executeQuery("select * from vista_bodega where categoria= "+ "'" + catego +"'");
																													
			while (resultado.next()) {
				VistaBodega oe = new VistaBodega();

				oe.setIdproducto(resultado.getInt(1));
				oe.setCategoria(resultado.getString(2));
				oe.setNombre(resultado.getString(3));
				oe.setStock(resultado.getInt(4));
				oe.setCosto(resultado.getDouble(5));
				oe.setIdsocio(resultado.getInt(6));
				listaProductoCatego.add(oe);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaProductoCatego;

	}

	public int getIdNotaEntrega(String num) {
		Conexion con = new Conexion();

		try {
			Connection connection = con.ObtenerConexion();
			Statement statement = connection.createStatement();

			ResultSet resultado = statement.executeQuery(
					"select idnotadeeentrega from vista_notaentrega where numero_nota_entrega = '" + num + "'");// susceptible
																												// de
																												// sql
			// injection
			while (resultado.next()) {
				return resultado.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getNumNotaEntrega(String num) {
		Conexion con = new Conexion();

		try {
			Connection connection = con.ObtenerConexion();
			Statement statement = connection.createStatement();

			ResultSet resultado = statement.executeQuery(
					"select numero_nota_entrega from vista_notaentrega where numero_nota_entrega = '" + num + "'");// susceptible
																												// de
																												// sql
			// injection
			while (resultado.next()) {
				return resultado.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getNumFact(String num) {
		Conexion con = new Conexion();

		try {
			Connection connection = con.ObtenerConexion();
			Statement statement = connection.createStatement();

			ResultSet resultado = statement.executeQuery(
					"select numerofactura from vista_factura where numerofactura = '" + num + "'");
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
