package controladorUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.Conexion;
import modeloEntidades.Usuario;

public class UsuarioDAO {

	private static final String Insertar = "INSERT INTO usuario (nombre, contraseña, tipousuario) values (?,?,?)";
	private static final String Consultar = "Select * from usuario ";

	public static boolean insertarUsuarioAlaBaseDeDatos(Usuario oe) {

		Conexion con = new Conexion();
		boolean op = false;
		try (Connection connection = con.ObtenerConexion()) {
			PreparedStatement pst = connection.prepareStatement(Insertar);

			pst.setString(1, oe.getNombre());
			pst.setString(2, oe.getContraseña());
			pst.setString(3, oe.getTipousuario());

			int n = pst.executeUpdate();
			if (n != 0) {
				op = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return op;

	}

	public static boolean SearchExisteUsuario(String usuario) {
		Conexion con = new Conexion();
		boolean x = false;
		try {
			Connection connection = con.ObtenerConexion();
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery(Consultar + " where nombre= '" + usuario + "'");
			while (resultado.next()) {

				x = true;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return x;

	}

	public static boolean Login(Usuario usr) throws SQLException {

		Conexion con = new Conexion();
		Connection connection = con.ObtenerConexion();
		try {
			Statement statement = connection.createStatement();

			ResultSet resultado = statement.executeQuery(Consultar + " where nombre= '" + usr.getNombre() + "'");
			while (resultado.next()) {
				String usuario = usr.getNombre();

				String contra = usr.getContraseña();

				String contraseña = resultado.getString(3);

				String usuarioAux = resultado.getString(2);

				if (contra.compareTo(contraseña) == 0) {

					if (usuario.compareTo(usuarioAux) == 0) {
						usr.setTipousuario(resultado.getString(4));
						return true;
					}

				} else {

					return false;
				}

			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}

	}
	
	
}
