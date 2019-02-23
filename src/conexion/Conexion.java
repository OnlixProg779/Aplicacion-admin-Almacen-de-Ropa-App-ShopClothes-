package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static Connection conexion = null;
	static final String usr = "postgres";
	private static final String pswd = "PDmyLove.!21";
	private static final String url = "jdbc:postgresql://127.0.0.1:5432/AlmacenDeRopa3";

	public Conexion() {

	}

	/**
	 * Class.forName("org.postgresql.Driver") es la Clase usada para el driver
	 * conexion= DriverManager.getConnection(url, usr, pswd), enviammos los datos
	 * para la coneccion a la base de datos
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection ObtenerConexion() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection(url, usr, pswd);
			System.out.println("conexion exitosa");
		} catch (ClassNotFoundException | SQLException arg) {
			throw new RuntimeException(arg.getMessage() + " error en datos.Conexion");
		}
		return conexion;
	}

	public void empezarConexion() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException exeption) {
			// TODO Auto-generated catch block
			exeption.printStackTrace();
		}
		Conexion empezar = new Conexion();
		try {
			Connection con = empezar.ObtenerConexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error de Conexion");
		}
	}
}
