package controladorEstructuraDeDatos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controladorBodega.ControladorBodegaDAO;
import controladorBodega.ControladorNotaDeEntregaDAO;
import controladorClientes.ControladorClientesDAO;
import controladorCuentas.ControladorCuentasBancosDAO;
import controladorCuentas.ControladorCuentasCajaDAO;
import controladorCuentas.ControladorCuentasChequesDAO;
import controladorCuentas.ControladorCuentasCuentasPorCobrarDAO;
import controladorCuentas.ControladorCuentasCuentasPorPagarDAO;
import controladorCuentas.ControladorCuentasDepositosDAO;
import controladorFacturacion.ControladorFacturarDAO;
import controladorReportes.ControladorReportesCuentasCobradasDAO;
import controladorReportes.ControladorReportesCuentasPagadasDAO;
import controladorReportes.ControladorVentasPorSocioDAO;
import controladorSocios.ControladorSociosVerSociosDAO;
import controladorVistaReportes.ControladorVentanaReportesVerVentas;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.Banco;
import modeloEntidades.Categoria;
import modeloEntidades.Cliente;
import modeloEntidades.Cuenta;
import modeloEntidades.CuentasPorPagar;
import modeloEntidades.MovimientosBancario;
import modeloEntidades.Socio;
import modeloEntidades.VistaBodega;
import modeloEntidades.VistaCaja;
import modeloEntidades.VistaCheque;
import modeloEntidades.VistaCuenta;
import modeloEntidades.VistaCuentasPorCobrar;

import modeloEntidades.VistaDeposito;
import modeloEntidades.VistaVentasSocio;
import vista.Menu_1;

public class ControladorListasYArboles {

	public static ControladorBodegaDAO controladorBodegaDAO = new ControladorBodegaDAO();
	

	public static ControladorFacturarDAO controladorFacturarDAO = new ControladorFacturarDAO();

	public static ControladorClientesDAO controladorClientesDAO = new ControladorClientesDAO();

	public static ControladorCuentasBancosDAO controladorCuentasBancosDAO = new ControladorCuentasBancosDAO();
	public static ControladorCuentasCajaDAO controladorCuentasCajaDAO = new ControladorCuentasCajaDAO();
	public static ControladorCuentasChequesDAO controladorCuentasChequesDAO = new ControladorCuentasChequesDAO();
	public static ControladorCuentasCuentasPorCobrarDAO controladorCuentasCuentasPorCobrarDAO = new ControladorCuentasCuentasPorCobrarDAO();
	public static ControladorCuentasCuentasPorPagarDAO controladorCuentasCuentasPorPagarDAO = new ControladorCuentasCuentasPorPagarDAO();
	public static ControladorCuentasDepositosDAO controladorCuentasDepositosDAO = new ControladorCuentasDepositosDAO();

	public static ControladorReportesCuentasCobradasDAO controladorReportesCuentasCobradasDAO = new ControladorReportesCuentasCobradasDAO();
	public static ControladorReportesCuentasPagadasDAO controladorReportesCuentasPagadasDAO = new ControladorReportesCuentasPagadasDAO();

	public static ControladorSociosVerSociosDAO controladorSociosVerSociosDAO = new ControladorSociosVerSociosDAO();

	public static ControladorNotaDeEntregaDAO controladorNotaDeEntregaDAO = new ControladorNotaDeEntregaDAO();
	
	public static ControladorVentasPorSocioDAO controladorVentasPorSocioDAO = new ControladorVentasPorSocioDAO();
	/**
	 * CAJA
	 */

	public static ArbolAVL<VistaCaja> arbolVistaCaja = new ArbolAVL<>();

	public static void actualizarArbolCaja(Menu_1 vista) {

		arbolVistaCaja.vaciar();
		try {

			arbolVistaCaja = controladorCuentasCajaDAO.listarCaja();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * BANCOS
	 */
	public static List<Banco> listadoBancos = new ArrayList<>();
	public static ArbolAVL<VistaCuenta> arbolCuentasBancarias = new ArbolAVL<>();
	public static ArbolAVL<MovimientosBancario> arbolMovimientosBancarios = new ArbolAVL<>();

	public static void actualizarListaBancos(Menu_1 vista) {
		listadoBancos.clear();
		try {

			listadoBancos = controladorCuentasBancosDAO.listarBancosCompletos();
			// System.out.println(listadoBancos);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void actualizarArbolCuentasBancarias(Menu_1 vista) {
		arbolCuentasBancarias.vaciar();
		try {

			arbolCuentasBancarias = controladorCuentasBancosDAO.listarCuentasBancarias();
			// System.out.println(listadoBancos);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void actualizarArbolMovimientosBancarios(Menu_1 vista) {
		arbolMovimientosBancarios.vaciar();
		try {

			arbolMovimientosBancarios = controladorCuentasBancosDAO.listarMovimientosBancarios();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * DEPOSITOS
	 */

	public static ArbolAVL<VistaDeposito> arbolVistaDeposito = new ArbolAVL<>();

	public static void actualizarArbolDepositos(Menu_1 vista) {
		arbolVistaDeposito.vaciar();
		try {

			arbolVistaDeposito = controladorCuentasDepositosDAO.listarDepositos();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * CHEQUES
	 */

	public static ArbolAVL<VistaCheque> arbolVistaCheques = new ArbolAVL<>();

	public static void actualizarArbolCheques(Menu_1 vista) {
		arbolVistaCheques.vaciar();
		try {

			arbolVistaCheques = controladorCuentasChequesDAO.listarCheques();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * CUENTAS POR PAGAR
	 */

	public static ArbolAVL<CuentasPorPagar> arbolVistaCXP = new ArbolAVL<>();

	public static void actualizarArbolCXP(Menu_1 vista) {
		arbolVistaCXP.vaciar();
		try {

			arbolVistaCXP = controladorCuentasCuentasPorPagarDAO.listarCuentasPorPagar();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * CUENTAS POR COBRAR
	 */

	public static ArbolAVL<VistaCuentasPorCobrar> arbolVistaCXC = new ArbolAVL<>();

	public static void actualizarArbolCXC(Menu_1 vista) {
		arbolVistaCXC.vaciar();
		try {

			arbolVistaCXC = controladorCuentasCuentasPorCobrarDAO.listarCuentasPorCobrar();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * FACTURACION
	 */

	/**
	 * DEVOLUCION DE CLIENTES
	 */

	

	/**
	 * SOCIOS VER SOCIOS
	 */
	public static ArbolAVL<Socio> arbolSocios = new ArbolAVL<>();

	public static void actualizarArbolSocios(Menu_1 vista) {
		arbolSocios.vaciar();
		try {

			arbolSocios = controladorSociosVerSociosDAO.listarSociosEnArbol();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * CLIENTES
	 */
	public static ArbolAVL<Cliente> arboVistaClientes = new ArbolAVL<>();

	public static void actualizarArbolClientes(Menu_1 vista) {
		arboVistaClientes.vaciar();
		try {

			arboVistaClientes = controladorClientesDAO.listarClientes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * BODEGA REVISAR BODEGA
	 */
	public static ArbolAVL<VistaBodega> arboVistaBodega = new ArbolAVL<>();

	public static void actualizarArbolBodega(Menu_1 vista) {
		arboVistaBodega.vaciar();
		try {

			arboVistaBodega = controladorBodegaDAO.listarProductosBodega();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static List<Categoria> listaCategorias = new ArrayList<>();
	public static void actualizarListaCategorias(Menu_1 vista) {
		listaCategorias.clear();
		try {

			listaCategorias = controladorBodegaDAO.listarCategorias();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * REPORTE CUENTAS PAGADAS
	 */
	public static ArbolAVL<modeloEntidades.CuentasPorPagar> arboVistaCuentasPagadas = new ArbolAVL<>();

	public static void actualizarArbolCuentasPagadas(Menu_1 vista) {
		arboVistaCuentasPagadas.vaciar();
		try {

			arboVistaCuentasPagadas = controladorReportesCuentasPagadasDAO.listarCuentasPagadas();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * REPORTE CUENTAS COBRADAS
	 */
	public static ArbolAVL<VistaCuentasPorCobrar> arboVistaCuentasCobradas = new ArbolAVL<>();

	public static void actualizarArbolCuentasCobradas(Menu_1 vista) {
		arboVistaCuentasCobradas.vaciar();
		try {

			arboVistaCuentasCobradas = controladorReportesCuentasCobradasDAO.listarCuentasCobradas();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ArbolAVL<modeloEntidades.VistaFactura> arboVistaFacturas = new ArbolAVL<>();
	
	public static void actualizarArbolFacturas(Menu_1 vista) {
		arboVistaFacturas.vaciar();
		try {

			arboVistaFacturas = controladorFacturarDAO.listarFacturas();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
public static List<VistaVentasSocio> ListaVistaVentasSocio = new ArrayList<>();
	
	public static void actualizarVentasDeSocio(Integer idSocio, java.sql.Date fechaDesde, java.sql.Date fechaHasta) {
		ListaVistaVentasSocio.clear();
		try {

			ListaVistaVentasSocio = controladorVentasPorSocioDAO.listarVentasDeSocio(idSocio, fechaDesde, fechaHasta);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
