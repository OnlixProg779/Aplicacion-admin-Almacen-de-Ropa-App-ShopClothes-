package controladorCuentas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import conexion.Conexion;
import controladorFacturacion.TablasTemporales;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.Deposito;
import modeloEntidades.VistaDeposito;

public class ControladorCuentasDepositosDAO {

	private static final String Insertar = "Insert into despositos values(?,?,?,?,?,?,?,?,?)";
	private static final String Consultar = "Select * from despositos";
	private static final String Eliminar = "Delete from despositos where iddepositos=?";
	private static final String Actualizar = "Update despositos set iddepositos = ?,idbanco = ?, n_deposito = ?, fecha= ?, efectivo = ?,cheque=?,depositante=?,descripcion=?, total=? where iddepositos =?";
	private static final String ConsultarId = "Select * from despositos where iddepositos =?";

	private static final String SP_INSERTAR_DEPOSITO = "{call ingresar_deposito(?,?,?,?,?,?,?) }";

	private static final String LISTAR = "Select * from vista_depositos";

	public ArbolAVL<VistaDeposito> listarDepositos() throws SQLException {
		Conexion con = new Conexion();
		Connection connection = con.ObtenerConexion();
		ArbolAVL<VistaDeposito> listaVistaDepositos = new ArbolAVL<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery(LISTAR);// susceptible de sql injection
			while (resultado.next()) {
				VistaDeposito oe = new VistaDeposito();
				oe.setIddeposito(resultado.getInt(1));
				oe.setTitular(resultado.getString(2));
				oe.setNcuenta(resultado.getString(3));
				oe.setTipo(resultado.getString(4));
				oe.setNDeposito(resultado.getString(5));
				oe.setFecha((resultado.getDate(6)));
				oe.setEfectivo(resultado.getDouble(7));
				oe.setNCheque(resultado.getInt(8));
				oe.setChequeBanco(resultado.getString(9));
				oe.setDetallecheque(resultado.getString(10));
				oe.setValor(resultado.getDouble(11));
				oe.setDepositante(resultado.getString(12));
				oe.setDescripcion(resultado.getString(13));
				oe.setTotal(resultado.getDouble(14));
				listaVistaDepositos.add(oe);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaVistaDepositos;
	}

	public DefaultTableModel mostrarTablaVistaDepositos(ArbolAVL<VistaDeposito> arbol) {
		String[] titulos = { "IdDeposito", "N° Cuenta", "Num.Deposito", "Fecha", "Efectivo", "Num.Cheque",
				"Cheque Banco", "Valor", "Depositante", "Descripcion", "Total" };
		String[] registro = new String[11];
		DefaultTableModel modelo = new DefaultTableModel(null, titulos);
		for (VistaDeposito oe : arbol.inOrdenList()) {
			registro[0] = String.valueOf(oe.getIddeposito());
			registro[1] = oe.getNcuenta();
			registro[2] = oe.getNDeposito();
			registro[3] = String.valueOf(oe.getFecha());
			registro[4] = String.valueOf(oe.getEfectivo());
			registro[5] = String.valueOf(oe.getNCheque());
			registro[6] = oe.getChequeBanco();
			registro[7] = String.valueOf(oe.getValor());
			registro[8] = oe.getDepositante();
			registro[9] = oe.getDescripcion();
			registro[10] = String.valueOf(oe.getTotal());
			modelo.addRow(registro);
		}
		return modelo;
	}

	public void insertarDeposito(Deposito deposito) throws SQLException {

		Conexion con = new Conexion();
		Connection connection = con.ObtenerConexion();

		CallableStatement cst = connection.prepareCall(SP_INSERTAR_DEPOSITO);

		cst.setInt(1, deposito.getIdcuenta());
		cst.setString(2, deposito.getNDeposito());

		cst.setDate(3, java.sql.Date.valueOf(TablasTemporales.asLocalDate(deposito.getFecha())));

		cst.setDouble(4, deposito.getEfectivo());
		cst.setInt(5, deposito.getCheque());
		cst.setString(6, deposito.getDepositante());
		cst.setString(7, deposito.getDescripcion());

		if (cst.execute()) {
			JOptionPane.showMessageDialog(null, "Deposito Ingresado", "Aviso", JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Error al ingresar en la Base de datos", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}
