package controladorCuentas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import conexion.Conexion;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.CuentasPorPagar;

public class ControladorCuentasCuentasPorPagarDAO {

	private static final String LISTAR = "Select * from cuentas_por_pagar";

	public ArbolAVL<CuentasPorPagar> listarCuentasPorPagar() throws SQLException {
		Conexion con = new Conexion();
		Connection connection = con.ObtenerConexion();
		ArbolAVL<CuentasPorPagar> listaVistaCuentasPorPagar = new ArbolAVL<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery(LISTAR + " where estado = 'PENDIENTE'");// susceptible de sql
																									// injection
			while (resultado.next()) {
				CuentasPorPagar oe = new CuentasPorPagar();
				oe.setIdcuentaxpagar(resultado.getInt(1));
				oe.setFecha((resultado.getDate(2)));
				oe.setValor(resultado.getDouble(3));
				oe.setDescripcion(resultado.getString(4));
				oe.setEstado(resultado.getString(5));
				listaVistaCuentasPorPagar.add(oe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaVistaCuentasPorPagar;
	}

	public DefaultTableModel mostrarTablaCuentasPorPagar(ArbolAVL<CuentasPorPagar> arbol) {
		String[] titulos = { "IdCuentaPorPagar", "Fecha", "Valor", "Descripcion", "Estado" };
		String[] registro = new String[5];
		DefaultTableModel modelo = new DefaultTableModel(null, titulos);
		for (CuentasPorPagar oe : arbol.inOrdenList()) {
			registro[0] = String.valueOf(oe.getIdcuentaxpagar());
			registro[1] = String.valueOf(oe.getFecha());
			registro[2] = String.valueOf(oe.getValor());
			registro[3] = oe.getDescripcion();
			registro[4] = oe.getEstado();
			modelo.addRow(registro);
		}
		return modelo;
	}

	public boolean actualizarCuenta(int idcuenta) throws SQLException {
		boolean op = false;
		Conexion con = new Conexion();
		Connection connection = con.ObtenerConexion();
		PreparedStatement statement = connection.prepareStatement(
				"update cuentas_por_pagar set estado='PAGADO' where idcuentaxpagar= '" + idcuenta + "'");
		int n = statement.executeUpdate();
		if (n != 0) {
			op = true;
		}
		return op;
	}
}
