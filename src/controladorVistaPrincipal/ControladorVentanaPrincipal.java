package controladorVistaPrincipal;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Animacion.Animacion;
import conexion.Conexion;
import controladorEstructuraDeDatos.ControladorListasYArboles;
import controladorFacturacion.ControladorReportesFactura;
import controladorUsuario.UsuarioDAO;
import controladorVistaBodega.ControladorVentanaBodegaNuevoProducto;
import controladorVistaBodega.ControladorVentanaBodegaRevisar;
import controladorVistaBodega.ControladorVentanaDevolucionesFacturadas;
import controladorVistaBodega.ControladorVentanaNotaDeEntrega;
import controladorVistaBodega.ControladorVentanaSociosDevoluciones;
import controladorVistaClientes.ControladorVentanaClientesNuevoCliente;
import controladorVistaClientes.ControladorVentanaClientesVer;
import controladorVistaCuentas.ControladorVentanaCuentasBancos;
import controladorVistaCuentas.ControladorVentanaCuentasCaja;
import controladorVistaCuentas.ControladorVentanaCuentasCheques;
import controladorVistaCuentas.ControladorVentanaCuentasDepositos;
import controladorVistaCuentas.ControladorVentanaCuentasPorCobrar;
import controladorVistaCuentas.ControladorVentanaCuentasPorPagar;
import controladorVistaFacturacion.ControladorVentanaNuevaFactura;
import controladorVistaReportes.ControladorVentanaReportesCuentasCobradas;
import controladorVistaReportes.ControladorVentanaReportesCuentasPagadas;
import controladorVistaReportes.ControladorVentanaReportesVentasPorSocio;
import controladorVistaReportes.ControladorVentanaReportesVerVentas;
import controladorVistaSocios.ControladorVentanaSociosNuevoSocio;
import controladorVistaSocios.ControladorVentanaSociosVerSocios;
import estructuraDeDatos.ArbolAVL;

import modeloEntidades.VistaDetallefactura;
import modeloEntidades.VistaFactura;
import pruebas.Iniciador;
import modeloEntidades.Categoria;
import modeloEntidades.Socio;
import modeloEntidades.Usuario;
import vista.Hash;
import vista.JInternalFrameInicioSesion;
import vista.Menu_1;
import vista.PanelInicioSesion;
import vistaBodega.JInternalFrameRevisarBodega;
import vistaBodega.JInternalFrameBodegaDevolucionesASocios;
import vistaBodega.JFrameBodegaNuevoProducto;
import vistaBodega.JInternalFrameBodegaDevolucionesDeClientes;

import vistaBodega.JInternalFrameNotaDeEntrega;
import vistaClientes.JFrameClientesNuevoCliente;
import vistaClientes.JInternalFrameClientesVer;
import vistaCuentas.JInternalFrameCuentasBancos;
import vistaCuentas.JInternalFrameCuentasCaja;
import vistaCuentas.JInternalFrameCuentasCheques;
import vistaCuentas.JInternalFrameCuentasDepositos;
import vistaCuentas.JInternalFrameCuentasPorCobrar;
import vistaCuentas.JInternalFrameCuentasPorPagar;
import vistaFacturacion.JInternalFrameFacturacionNuevaFactura;
import vistaFacturacion.Panel_Factura;
import vistaReportes.JInternalFrameReportesCuentasCobradas;
import vistaReportes.JInternalFrameReportesCuentasPagadas;
import vistaReportes.JInternalFrameReportesVentasPorSocio;
import vistaReportes.JInternalFrameReportesVerVentas;
import vistaSocios.JFrameSociosNuevoSocio;
import vistaSocios.JInternalFrameSociosVerSocios;

public class ControladorVentanaPrincipal implements ActionListener {

	public ControladorVentanaCuentasCaja controladorVentanaCuentasCaja;
	public ControladorVentanaCuentasBancos controladorVentanaCuentasBancos;
	public ControladorVentanaCuentasDepositos controladorVentanaCuentasDepositos;
	public ControladorVentanaCuentasCheques controladorVentanaCuentasCheques;
	public ControladorVentanaCuentasPorPagar controladorVentanaCuentasPorPagar;
	public ControladorVentanaCuentasPorCobrar controladorVentanaCuentasPorCobrar;
	public ControladorVentanaNuevaFactura controladorVentanaNuevaFactura;
	public ControladorVentanaDevolucionesFacturadas controladorVentanaDevolucionesFacturadas;
	public ControladorVentanaSociosVerSocios controladorVentanaSociosVerSocios;
	public ControladorVentanaSociosNuevoSocio controladorVentanaSociosNuevoSocio;
	public ControladorVentanaSociosDevoluciones controladorVentanaSociosDevoluciones;
	public ControladorVentanaClientesVer controladorVentanaClientesVer;
	public ControladorVentanaClientesNuevoCliente controladorVentanaClientesNuevoCliente;
	public ControladorVentanaBodegaRevisar controladorVentanaBodegaRevisar;
	public ControladorVentanaBodegaNuevoProducto controladorVentanaBodegaNuevoProducto;
	public ControladorVentanaNotaDeEntrega controladorVentanaNotaDeEntrega;
	public ControladorVentanaReportesVerVentas controladorVentanaReportesVerVentas;
	public ControladorVentanaReportesCuentasPagadas controladorVentanaReportesCuentasPagadas;
	public ControladorVentanaReportesCuentasCobradas controladorVentanaReportesCuentasCobradas;
	public ControladorVentanaReportesVentasPorSocio controladorVentanaReportesVentasPorSocio;
	private String num;
	private Menu_1 vista;
	private String rol;
	
	

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public ControladorVentanaPrincipal(Menu_1 vista, String rol) {
		super();
		this.vista = vista;
		this.rol = rol;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Cuentas Caja")) {
			openCuentasCaja();
		}

		else if (e.getActionCommand().equals("Cuentas Bancos")) {
			openCuentasBancos();

		} else if (e.getActionCommand().equals("Cuentas Depositos")) {
			openCuentasDepositos();

		} else if (e.getActionCommand().equals("Cuentas Cheques")) {
			openCuentasCheques();

		} else if (e.getActionCommand().equals("Cuentas Por Pagar")) {
			openCuentasPorPagar();

		} else if (e.getActionCommand().equals("Cuentas Por Cobrar")) {
			openCuentasPorCobrar();

		} else if (e.getActionCommand().equals("Facturacion Nueva Factura")) {
			openFacturacionNuevaFactura();

		} else if (e.getActionCommand().equals("Socios Ver Socios")) {
			openSociosVerSocios();

		} else if (e.getActionCommand().equals("Socios Nuevo Socio") && rol.equals("ADMINISTRADOR")) {
			openSociosNuevoSocio();

		} else if (e.getActionCommand().equals("Socios Mercaderia")) {
			// openSociosMercaderia();

		} else if (e.getActionCommand().equals("Clientes Ver Clientes")) {
			openClientesVerClientes();

		} else if (e.getActionCommand().equals("Clientes Nuevo Cliente")) {
			openClientesNuevoCliente();

		} else if (e.getActionCommand().equals("Bodega Revisar Bodega")) {
			openBodegaRevisarBodega();

		} else if (e.getActionCommand().equals("Bodega Nota de Entrega")) {
			openBodegaNotadeEntrega();

		} else if (e.getActionCommand().equals("Bodega Devoluciones de Socios")) {
			openBodegaDevolucionesASocios();

		} else if (e.getActionCommand().equals("Bodega Devoluciones De Clientes")) {
			openBodegaDevolucionesDeClientes();

		} else if (e.getActionCommand().equals("Reportes Ver Ventas")) {
			openReportesVerVentas();

		} else if (e.getActionCommand().equals("Reportes Cuentas Pagadas")) {
			openReportesCuentasPagadas();

		} else if (e.getActionCommand().equals("Reportes Cuentas Cobradas")) {
			openReportesCuentasCobradas();

		} else if (e.getActionCommand().equals("Reportes Ventas Por Socio") && rol.equals("ADMINISTRADOR")) {
			openReportesVentasPorSocio();

		} else if (e.getActionCommand().equals("Ver Facturas")) {
			openReporteFacturas();

		} else if (e.getActionCommand().equals("Ver Notas De Entrega")) {
			openReporteNotaDeEntrega();

		} else if (e.getActionCommand().equals("Cambiar Sesion")) {

			//cambiarSesion();

		} else if (e.getActionCommand().equals("Nuevo Usuario") && rol.equals("ADMINISTRADOR")) {

			nuevoUsuario();

		} else if (e.getActionCommand().equals("Proveedores Ver Proveedores")) {
			openProveedores();

		}

	}

	public void openCuentasCaja() {
		vista.getFrame().getContentPane().remove(4);
		controladorVentanaCuentasCaja = new ControladorVentanaCuentasCaja(vista, rol);
		vista.internalFrameCuentasCaja = new JInternalFrameCuentasCaja(controladorVentanaCuentasCaja);
		vista.getFrame().getContentPane().add(vista.internalFrameCuentasCaja);

		ControladorListasYArboles.actualizarArbolCaja(vista);
		controladorVentanaCuentasCaja.actualizarTabla();

		vista.internalFrameCuentasCaja.setVisible(true);

		controladorVentanaCuentasCaja.actualizarSaldoDeCaja();

	}

	public void openCuentasBancos() {

		vista.getFrame().getContentPane().remove(4);
		controladorVentanaCuentasBancos = new ControladorVentanaCuentasBancos(vista, rol);
		vista.internalFrameCuentasBancos = new JInternalFrameCuentasBancos(controladorVentanaCuentasBancos);
		vista.getFrame().getContentPane().add(vista.internalFrameCuentasBancos);

		ControladorListasYArboles.actualizarListaBancos(vista);

		vista.internalFrameCuentasBancos.getPanel().comboBoxBanco
				.setModel(controladorVentanaCuentasBancos.actualizarItemsBancos());
		vista.internalFrameCuentasBancos.setVisible(true);

	}

	public void openCuentasDepositos() {
		vista.getFrame().getContentPane().remove(4);
		controladorVentanaCuentasDepositos = new ControladorVentanaCuentasDepositos(vista);
		vista.internalFrameCuentasDepositos = new JInternalFrameCuentasDepositos(controladorVentanaCuentasDepositos);
		vista.getFrame().getContentPane().add(vista.internalFrameCuentasDepositos);

		ControladorListasYArboles.actualizarArbolDepositos(vista);

		controladorVentanaCuentasDepositos.actualizarTabla();

		vista.internalFrameCuentasDepositos.setVisible(true);
	}

	public void openCuentasCheques() {
		vista.getFrame().getContentPane().remove(4);
		controladorVentanaCuentasCheques = new ControladorVentanaCuentasCheques(vista, rol);
		vista.internalFrameCuentasCheques = new JInternalFrameCuentasCheques(controladorVentanaCuentasCheques);
		vista.getFrame().getContentPane().add(vista.internalFrameCuentasCheques);

		ControladorListasYArboles.actualizarArbolCheques(vista);
		controladorVentanaCuentasCheques.actualizarTabla();

		vista.internalFrameCuentasCheques.setVisible(true);
	}

	public void openCuentasPorPagar() {
		vista.getFrame().getContentPane().remove(4);
		controladorVentanaCuentasPorPagar = new ControladorVentanaCuentasPorPagar(vista);
		vista.internalFrameCuentasPorPagar = new JInternalFrameCuentasPorPagar(controladorVentanaCuentasPorPagar);
		vista.getFrame().getContentPane().add(vista.internalFrameCuentasPorPagar);

		ControladorListasYArboles.actualizarArbolCXP(vista);
		controladorVentanaCuentasPorPagar.actualizarTabla();

		vista.internalFrameCuentasPorPagar.setVisible(true);
	}

	public void openCuentasPorCobrar() {
		vista.getFrame().getContentPane().remove(4);
		controladorVentanaCuentasPorCobrar = new ControladorVentanaCuentasPorCobrar(vista);
		vista.internalFrameCuentasPorCobrar = new JInternalFrameCuentasPorCobrar(controladorVentanaCuentasPorCobrar);
		vista.getFrame().getContentPane().add(vista.internalFrameCuentasPorCobrar);

		ControladorListasYArboles.actualizarArbolCXC(vista);
		controladorVentanaCuentasPorCobrar.actualizarTabla();

		vista.internalFrameCuentasPorCobrar.setVisible(true);
	}

	public void openFacturacionNuevaFactura() {
		vista.getFrame().getContentPane().remove(4);
		controladorVentanaNuevaFactura = new ControladorVentanaNuevaFactura(vista);
		vista.internalFrameFactura = new JInternalFrameFacturacionNuevaFactura(controladorVentanaNuevaFactura);
		vista.getFrame().getContentPane().add(vista.internalFrameFactura, BorderLayout.CENTER);

		ControladorListasYArboles.actualizarArbolBodega(vista);
		vista.internalFrameFactura.getPanelFactura().getPanel_productos().getPanelProductosTabla().tableProductos
				.setModel(Iniciador.controladorPrincipal.controladorVentanaNuevaFactura
						.mostrarTablaBodega(ControladorListasYArboles.arboVistaBodega));

		try {
			vista.internalFrameFactura.getPanelFactura().getPanelEncabezado().lblSerie
					.setText(vista.internalFrameFactura.getPanelFactura().getPanelEncabezado().lblSerie.getText()
							+ (String.valueOf(ControladorListasYArboles.controladorFacturarDAO.num_factura() + 1)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vista.internalFrameFactura.getPanelFactura().getPanel_productos().getPanelProductosTabla().lblCostoCu
				.setText("PVP c/u");
		vista.internalFrameFactura.getPanelFactura().getPanelEncabezado().lblFechaActual
				.setText(controladorVentanaNuevaFactura.getFecha());
		vista.internalFrameFactura.setVisible(true);
	}

	public void openBodegaDevolucionesDeClientes() {

		vista.getFrame().getContentPane().remove(4);

		controladorVentanaDevolucionesFacturadas = new ControladorVentanaDevolucionesFacturadas(vista);
		vista.internalFrameBodegaDevolucionesDeClientes = new JInternalFrameBodegaDevolucionesDeClientes(
				controladorVentanaDevolucionesFacturadas);
		vista.getFrame().getContentPane().add(vista.internalFrameBodegaDevolucionesDeClientes, BorderLayout.CENTER);
		vista.internalFrameBodegaDevolucionesDeClientes.setVisible(true);

	}

	public void openSociosVerSocios() {
		vista.getFrame().getContentPane().remove(4);
		controladorVentanaSociosVerSocios = new ControladorVentanaSociosVerSocios(vista, rol);
		vista.internalFrameSociosVerSocios = new JInternalFrameSociosVerSocios(controladorVentanaSociosVerSocios);
		vista.getFrame().getContentPane().add(vista.internalFrameSociosVerSocios);

		ControladorListasYArboles.actualizarArbolSocios(vista);
		controladorVentanaSociosVerSocios.actualizarTabla();

		vista.internalFrameSociosVerSocios.setVisible(true);
	}

	public void openSociosNuevoSocio() {

		controladorVentanaSociosNuevoSocio = new ControladorVentanaSociosNuevoSocio(vista);
		vista.frameSociosNuevoSocio = new JFrameSociosNuevoSocio(controladorVentanaSociosNuevoSocio);
		vista.frameSociosNuevoSocio.setVisible(true);

	}

	public void openBodegaDevolucionesASocios() {

		vista.getFrame().getContentPane().remove(4);
		controladorVentanaSociosDevoluciones = new ControladorVentanaSociosDevoluciones(vista);
		vista.internalFrameBodegaDevolucionesASocios = new JInternalFrameBodegaDevolucionesASocios(
				controladorVentanaSociosDevoluciones);
		vista.getFrame().getContentPane().add(vista.internalFrameBodegaDevolucionesASocios);
		vista.internalFrameBodegaDevolucionesASocios.setVisible(true);

	}

	public void openClientesVerClientes() {
		vista.getFrame().getContentPane().remove(4);

		controladorVentanaClientesVer = new ControladorVentanaClientesVer(vista);
		vista.internalFrameCli = new JInternalFrameClientesVer(controladorVentanaClientesVer);
		vista.getFrame().getContentPane().add(vista.internalFrameCli, BorderLayout.CENTER);

		ControladorListasYArboles.actualizarArbolClientes(vista);
		controladorVentanaClientesVer.actualizarTabla();

		vista.internalFrameCli.setVisible(true);
	}

	public void openClientesNuevoCliente() {
		controladorVentanaClientesNuevoCliente = new ControladorVentanaClientesNuevoCliente(vista);
		vista.frameClientesNuevoCliente = new JFrameClientesNuevoCliente(controladorVentanaClientesNuevoCliente);

		vista.frameClientesNuevoCliente.setVisible(true);
	}

	public void openBodegaRevisarBodega() {
		vista.getFrame().getContentPane().remove(4);

		controladorVentanaBodegaRevisar = new ControladorVentanaBodegaRevisar(vista);
		vista.inernalFrameBodega = new JInternalFrameRevisarBodega(controladorVentanaBodegaRevisar);
		vista.getFrame().getContentPane().add(vista.inernalFrameBodega, BorderLayout.CENTER);

		ControladorListasYArboles.actualizarArbolBodega(vista);
		controladorVentanaBodegaRevisar.actualizarTabla();

		ControladorListasYArboles.actualizarArbolSocios(vista);
		ControladorListasYArboles.actualizarListaCategorias(vista);
		vista.inernalFrameBodega.getPanel().comboBoxCategoria.setModel(actualizarItemsCategoria());
		vista.inernalFrameBodega.getPanel().comboBoxSocio.setModel(actualizarItemsSocios());

		vista.inernalFrameBodega.setVisible(true);
	}

	public void openBodegaNuevoProducto() {
		controladorVentanaBodegaNuevoProducto = new ControladorVentanaBodegaNuevoProducto(vista);
		vista.frameBodegaNuevoProducto = new JFrameBodegaNuevoProducto(controladorVentanaBodegaNuevoProducto, null);
		vista.frameBodegaNuevoProducto.setVisible(true);
	}

	public void openBodegaNotadeEntrega() {
		vista.getFrame().getContentPane().remove(4);
		controladorVentanaNotaDeEntrega = new ControladorVentanaNotaDeEntrega(vista);
		vista.internalFrameIngresarFacturaCompra = new JInternalFrameNotaDeEntrega(controladorVentanaNotaDeEntrega);
		vista.getFrame().getContentPane().add(vista.internalFrameIngresarFacturaCompra, BorderLayout.CENTER);

		ControladorListasYArboles.actualizarArbolBodega(vista);
		ControladorListasYArboles.actualizarListaCategorias(vista);
		ControladorListasYArboles.actualizarArbolSocios(vista);

		vista.internalFrameIngresarFacturaCompra.getPanel().comboBoxSocios
				.setModel(controladorVentanaNotaDeEntrega.actualizarItemsSocios());
		vista.internalFrameIngresarFacturaCompra.setVisible(true);
	}

	public void openReportesVerVentas() {

		vista.getFrame().getContentPane().remove(4);
		controladorVentanaReportesVerVentas = new ControladorVentanaReportesVerVentas(vista);
		vista.internalFrameReportesVerventas = new JInternalFrameReportesVerVentas(controladorVentanaReportesVerVentas);
		vista.getFrame().getContentPane().add(vista.internalFrameReportesVerventas);
		vista.internalFrameReportesVerventas.setVisible(true);

	}

	public void openReportesCuentasPagadas() {

		vista.getFrame().getContentPane().remove(4);
		controladorVentanaReportesCuentasPagadas = new ControladorVentanaReportesCuentasPagadas(vista);
		vista.internalFrameReportesCuentasPagadas = new JInternalFrameReportesCuentasPagadas(
				controladorVentanaReportesCuentasPagadas);
		vista.getFrame().getContentPane().add(vista.internalFrameReportesCuentasPagadas);

		ControladorListasYArboles.actualizarArbolCuentasPagadas(vista);
		controladorVentanaReportesCuentasPagadas.actualizarTabla();

		vista.internalFrameReportesCuentasPagadas.setVisible(true);

	}

	public void openReportesCuentasCobradas() {
		vista.getFrame().getContentPane().remove(4);
		controladorVentanaReportesCuentasCobradas = new ControladorVentanaReportesCuentasCobradas(vista);
		vista.internalFrameReportesCuentasCobradas = new JInternalFrameReportesCuentasCobradas(
				controladorVentanaReportesCuentasCobradas);
		vista.getFrame().getContentPane().add(vista.internalFrameReportesCuentasCobradas);

		ControladorListasYArboles.actualizarArbolCuentasCobradas(vista);
		controladorVentanaReportesCuentasCobradas.actualizarTabla();

		vista.internalFrameReportesCuentasCobradas.setVisible(true);

	}

	public void openReportesVentasPorSocio() {

		vista.getFrame().getContentPane().remove(4);
		controladorVentanaReportesVentasPorSocio = new ControladorVentanaReportesVentasPorSocio(vista);
		vista.internalFrameReportesVentasPorSocios = new JInternalFrameReportesVentasPorSocio(
				controladorVentanaReportesVentasPorSocio);
		vista.getFrame().getContentPane().add(vista.internalFrameReportesVentasPorSocios);

		ControladorListasYArboles.actualizarArbolSocios(vista);

		vista.internalFrameReportesVentasPorSocios.getPanel().comboBox
				.setModel(controladorVentanaReportesVentasPorSocio.actualizarItemsSocios());

		vista.internalFrameReportesVentasPorSocios.setVisible(true);

	}

	private void openReporteNotaDeEntrega() {

	}

	private void openReporteFacturas() {

		ControladorListasYArboles.actualizarArbolFacturas(vista);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(10, 0));
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		JTable table = new JTable(mostrarFacturas(ControladorListasYArboles.arboVistaFacturas));

		scrollPane.setViewportView(table);
		while (true) {
			int x = JOptionPane.showConfirmDialog(null, panel, "Lista De Faturas", JOptionPane.OK_CANCEL_OPTION);

			if (x == JOptionPane.OK_OPTION && table.getSelectedRow() >= 0) {

				num = table.getValueAt(table.getSelectedRow(), 0).toString();
				ControladorReportesFactura.buscarFactura(num);
				ArbolAVL<VistaDetallefactura> detalleFactura = ControladorReportesFactura.cargarItemsFacturadosArbol();
				VistaFactura vistaFactura = ControladorReportesFactura.cargarFactura();

				ControladorVentanaNuevaFactura nueva = new ControladorVentanaNuevaFactura(vista);

				JFrame revisar = new JFrame("Reporte de Factura");

				revisar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				revisar.setName("Factura Temporal");
				revisar.setBounds(100, 100, 980, 676);

				Panel_Factura factura = new Panel_Factura();
				factura.getPanel_datosFactura().getBtnBuscarCliente().setVisible(false);
				factura.getPanel_datosFactura().getBtnGuardar().setVisible(false);
				factura.getPanel_datosFactura().getBtnNuevoCliente().setVisible(false);
				factura.getPanel_productos().getPanelProductosTabla().setVisible(false);
				factura.getPanel_datosFactura().txtCedula.setEnabled(false);
				factura.getPanel_datosFactura().chckbxCredito.setSelected(vistaFactura.getCredito());
				factura.getPanel_datosFactura().chckbxCredito.setEnabled(false);
				factura.getPanelEncabezado().lblSerie.setText("Serie " + vistaFactura.getNumerofactura());
				factura.getPanelEncabezado().lblFechaActual.setText(vistaFactura.getFecha().toString());
				revisar.getContentPane().add(factura, BorderLayout.CENTER);

				factura.getPanel_datosFactura().txtCedula.setText(vistaFactura.getClienteCedula());
				factura.getPanel_datosFactura().txtDireccion.setText(vistaFactura.getDireccion());
				factura.getPanel_datosFactura().txtNombre.setText(vistaFactura.getNombre());
				factura.getPanel_datosFactura().txtTelefono.setText(vistaFactura.getTelefono());
				factura.getPanel_datosFactura().getBtnImprimir().setActionCommand("imprimirDeReporte");
				factura.getPanel_productos().txtTotal.setText(String.valueOf(vistaFactura.getTotal()));
				factura.getPanel_productos().txtIva.setText(String.valueOf(vistaFactura.getIva()));
				factura.getPanel_productos().txtSubTotal.setText(String.valueOf(vistaFactura.getSubtotal()));
				factura.getPanel_productos().tableFactura.setModel(nueva.mostrarTablaFactura(detalleFactura));
				factura.setControlador(nueva);
				revisar.setLocationRelativeTo(null);
				revisar.setVisible(true);
				return;

			} else if (x == JOptionPane.CANCEL_OPTION || x == JOptionPane.CLOSED_OPTION) {
				return;
			} else {
				JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna factura");
			}
		}

	}

	public void nuevoUsuario() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2, 5, 5));
		JTextField txtUsuario = new JTextField();
		JTextField txtContraseña = new JTextField();
		JLabel lblUsuario = new JLabel("Usuario");
		JLabel lblContraseña = new JLabel("Contraseña");
		JLabel lblRol = new JLabel("Rol");
		JTextField txtContraseña2 = new JTextField();
		JLabel lblContraseña2 = new JLabel("Confirma Contraseña");
		JComboBox<String> combobox = new JComboBox<>(new String[] { "<<SELECCIONE>>", "ADMINISTRADOR", "EMPLEADO" });

		panel.add(lblUsuario);
		panel.add(txtUsuario);
		panel.add(lblRol);
		panel.add(combobox);
		panel.add(lblContraseña);
		panel.add(txtContraseña);
		panel.add(lblContraseña2);
		panel.add(txtContraseña2);

		int x = JOptionPane.showConfirmDialog(null, panel, "Nuevo Usuario", JOptionPane.OK_CANCEL_OPTION);

		if (x == JOptionPane.OK_OPTION) {

			try {
				if (!txtUsuario.getText().equals("") && !txtContraseña.getText().equals("")
						&& !txtContraseña2.getText().equals("") && combobox.getSelectedIndex() > 0) {

					String usuario = txtUsuario.getText();
					String contraseñaPersonal = txtContraseña.getText();
					String confirmarContraseña = txtContraseña2.getText();

					String contraseñaEncriptada = null;
					Usuario usuarioAux = new Usuario();

					UsuarioDAO opSql = new UsuarioDAO();

					if (!UsuarioDAO.SearchExisteUsuario(usuario)) {
						if (contraseñaPersonal.equals(confirmarContraseña)) {
							contraseñaEncriptada = Hash.sha1(contraseñaPersonal);

							usuarioAux.setNombre(usuario);
							usuarioAux.setContraseña(contraseñaEncriptada);

							usuarioAux.setTipousuario(combobox.getSelectedItem().toString());

							if (opSql.insertarUsuarioAlaBaseDeDatos(usuarioAux)) {
								JOptionPane.showMessageDialog(null,
										"Registro Guardado" + "\n" + "Usuario: " + usuario + "\n" + "Contraseña: "
												+ contraseñaPersonal,
										"Informacion Importante", JOptionPane.INFORMATION_MESSAGE);

							}

						} else {
							JOptionPane.showMessageDialog(null, "Las contaseñas no coinciden");
						}
					} else {
						JOptionPane.showMessageDialog(null, "El usuario ya se encuentra registrado");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Llene todos los campos", "Faltan Campos",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} else if (x == JOptionPane.CANCEL_OPTION || x == JOptionPane.CLOSED_OPTION) {

		} 
	}

	public void cambiarSesion() {

		
		vista.getFrame().dispose();

		conexion.Conexion conec = new Conexion();
		 conec.empezarConexion();
		 Menu_1 ventana;	
		 ventana = new Menu_1();
		 ventana.getFrame().setVisible(true);
		 ventana.getFrame().setLocationRelativeTo(null);
		 
		Iniciador.controlador =new ControladorVentanaPrincipalSecundario(ventana); 
		 
		 ventana.internalFrameInicioSesion = new JInternalFrameInicioSesion(Iniciador.controlador);

		 ventana.getFrame().getContentPane().remove(4);

		 ventana.getFrame().getContentPane().add(ventana.internalFrameInicioSesion);

		 ventana.internalFrameInicioSesion.setVisible(true);
		

	}

	public void openProveedores() {
		vista.getFrame().getContentPane().remove(4);
		// ControladorVentanaProveedores controlador = new
		// ControladorVentanaProveedores(vista);
		// vista.internalFrameProv = new JInternalFrameProveedores(controlador);
		vista.getFrame().getContentPane().add(vista.internalFrameProv, BorderLayout.CENTER);
		vista.internalFrameProv.setVisible(true);
	}

	public DefaultTableModel mostrarFacturas(ArbolAVL<VistaFactura> facturas) {
		String[] titulos = { "Numero Factura", "Cliente", "Fecha" };
		String[] registro = new String[3];
		DefaultTableModel modelo = new DefaultTableModel(null, titulos);

		for (VistaFactura oe : facturas.inOrdenList()) {
			registro[0] = oe.getNumerofactura();
			registro[1] = oe.getNombre() + " " + oe.getApellido();
			registro[2] = oe.getFecha().toString();

			modelo.addRow(registro);

		}
		return modelo;
	}

	public DefaultComboBoxModel<Socio> actualizarItemsSocios() {

		DefaultComboBoxModel<Socio> model = new DefaultComboBoxModel<Socio>();

		for (int i = 0; i < ControladorListasYArboles.arbolSocios.size(); i++) {
			if (i == 0) {
				model.addElement(new Socio("<<Seleccione>>", "", ""));
			}
			model.addElement(ControladorListasYArboles.arbolSocios.getPreOrden(i));

		}
		return model;

	}

	public DefaultComboBoxModel<Categoria> actualizarItemsCategoria() {

		DefaultComboBoxModel<Categoria> model = new DefaultComboBoxModel<Categoria>();

		for (int i = 0; i < ControladorListasYArboles.listaCategorias.size(); i++) {

			if (i == 0) {
				model.addElement(new Categoria("<<Seleccione>>"));
			}
			model.addElement(ControladorListasYArboles.listaCategorias.get(i));

		}
		return model;

	}



}
