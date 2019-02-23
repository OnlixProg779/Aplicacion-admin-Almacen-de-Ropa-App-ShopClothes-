package controladorCuentas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import conexion.Conexion;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.Banco;
import modeloEntidades.Deposito;
import modeloEntidades.MovimientosBancario;
import modeloEntidades.Socio;
import modeloEntidades.VistaCaja;
import modeloEntidades.VistaCuenta;

public class ControladorCuentasBancosDAO {

	private static final String LISTAR = "Select * from bancos";
	private static final String SP_CALCULAR_SALDO = "{call calcular_saldo_de_cuenta(?) }";

	public List<Banco> listarBancosCompletos() throws SQLException {

		Conexion con = new Conexion();
		Connection connection = con.ObtenerConexion();
		List<Banco> listaBancos = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery(LISTAR);// susceptible de sql injection
			while (resultado.next()) {
				
				Banco oe = new Banco();

				oe.setIdbanco(resultado.getInt(1));
				oe.setNombre((resultado.getString(2)));
				oe.setDescripcion(resultado.getString(3));

				listaBancos.add(oe);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaBancos;

	}

	

	public ArbolAVL<VistaCuenta> listarCuentasBancarias() throws SQLException {
		Conexion con = new Conexion();
		Connection connection = con.ObtenerConexion();
		ArbolAVL<VistaCuenta> listaCuentasAhorro = new ArbolAVL<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery("select *  from vista_cuenta");// susceptible de sql injection
			while (resultado.next()) {
				VistaCuenta oe = new VistaCuenta();

				oe.setIdcuenta(resultado.getInt(1));
				oe.setIdbanco((resultado.getInt(2)));
				oe.setBanco(resultado.getString(3));
				oe.setTipo(resultado.getString(4));
				oe.setNumeroCuenta(resultado.getString(5));
				oe.setNombre(resultado.getString(6));

				listaCuentasAhorro.add(oe);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCuentasAhorro;

	}

	public ArbolAVL<MovimientosBancario> listarMovimientosBancarios() throws SQLException {
		Conexion con = new Conexion();
		Connection connection = con.ObtenerConexion();
		ArbolAVL<MovimientosBancario> arbolMovimientosBancarios = new ArbolAVL<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery("select *  from movimientos_bancarios");// susceptible de sql
																									// injection
			while (resultado.next()) {
				MovimientosBancario oe = new MovimientosBancario();

				oe.setIdMovimientos(resultado.getInt(1));
				oe.setCodigo(resultado.getString(2));
				oe.setIdcuenta(resultado.getInt(3));
				oe.setFecha(resultado.getDate(4));
				oe.setDescripcion(resultado.getString(5));
				oe.setDeposito(resultado.getDouble(6));
				oe.setRetiro(resultado.getDouble(7));
				oe.setSaldo(resultado.getDouble(8));

				arbolMovimientosBancarios.add(oe);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arbolMovimientosBancarios;

	}

}
