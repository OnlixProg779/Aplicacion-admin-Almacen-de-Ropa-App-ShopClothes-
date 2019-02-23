package vista;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import vistaBodega.JInternalFrameRevisarBodega;
import vistaBodega.JInternalFrameBodegaDevolucionesASocios;
import vistaBodega.JFrameBodegaNuevoProducto;
import vistaBodega.JInternalFrameBodegaDevolucionesDeClientes;
import vistaBodega.JInternalFrameNotaDeEntrega;
import vistaClientes.JFrameClientesNuevoCliente;
import vistaClientes.JInternalFrameClientesVer;
import vistaCuentas.FrameNuevaCuenta;
import vistaCuentas.JFrameNuevoDeposito;
import vistaCuentas.JInternalFrameCuentasBancos;
import vistaCuentas.JInternalFrameCuentasCaja;
import vistaCuentas.JInternalFrameCuentasCheques;
import vistaCuentas.JInternalFrameCuentasDepositos;
import vistaCuentas.JInternalFrameCuentasPorCobrar;
import vistaCuentas.JInternalFrameCuentasPorPagar;
import vistaFacturacion.JInternalFrameFacturacionNuevaFactura;
import vistaProveedores.JInternalFrameProveedores;
import vistaReportes.JInternalFrameReportesCuentasCobradas;
import vistaReportes.JInternalFrameReportesCuentasPagadas;
import vistaReportes.JInternalFrameReportesVentasPorSocio;
import vistaReportes.JInternalFrameReportesVerVentas;
import vistaSocios.JFrameSociosNuevoSocio;
import vistaSocios.JInternalFrameSociosVerSocios;

import javax.swing.JMenu;

import javax.swing.JPanel;

import controladorVistaPrincipal.ControladorVentanaPrincipal;
import controladorVistaPrincipal.ControladorVentanaPrincipalSecundario;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class Menu_1 {

	private JFrame frame;
	public JInternalFrameProveedores internalFrameProv;

	public JInternalFrameCuentasCaja internalFrameCuentasCaja;
	public JInternalFrameCuentasBancos internalFrameCuentasBancos;
	public JInternalFrameCuentasDepositos internalFrameCuentasDepositos;
	public JInternalFrameCuentasCheques internalFrameCuentasCheques;
	public JInternalFrameCuentasPorCobrar internalFrameCuentasPorCobrar;
	public JInternalFrameCuentasPorPagar internalFrameCuentasPorPagar;

	public JInternalFrameFacturacionNuevaFactura internalFrameFactura;

	public JInternalFrameSociosVerSocios internalFrameSociosVerSocios;

	public JFrameSociosNuevoSocio frameSociosNuevoSocio;
	public JFrameNuevoDeposito frameDeposito;
	public FrameNuevaCuenta cuentaNueva;
	public JInternalFrameClientesVer internalFrameCli;
	public JFrameClientesNuevoCliente frameClientesNuevoCliente;

	public JFrameBodegaNuevoProducto frameBodegaNuevoProducto;
	public JInternalFrameRevisarBodega inernalFrameBodega;
	public JInternalFrameNotaDeEntrega internalFrameIngresarFacturaCompra;
	public JInternalFrameBodegaDevolucionesASocios internalFrameBodegaDevolucionesASocios;
	public JInternalFrameBodegaDevolucionesDeClientes internalFrameBodegaDevolucionesDeClientes;

	public JInternalFrameReportesVentasPorSocio internalFrameReportesVentasPorSocios;
	public JInternalFrameReportesCuentasCobradas internalFrameReportesCuentasCobradas;
	public JInternalFrameReportesCuentasPagadas internalFrameReportesCuentasPagadas;
	public JInternalFrameReportesVerVentas internalFrameReportesVerventas;

	//public ControladorVentanaPrincipal controladorVistaPrincipal;

	
	public JInternalFrameInicioSesion internalFrameInicioSesion;
	
	private JMenuItem mnProveedoresVer;
	private JMenuItem mnClientesVer;
	private JMenuItem mnFacturacionNuevaFactura;
	private JMenuItem mnBodegaRevisar;
	private JMenuItem mnReportesVerVentas;
	private JMenuItem mntmCaja;
	private JMenuItem mntmBancos;
	private JMenuItem mntmDepositos;
	private JMenuItem mntmCheques;
	private JMenuItem mntmCuentasPorPagar;
	private JMenuItem mntmCuentasPorCobrar;
	private JMenuItem mntmDevoluciones;
	private JMenuItem mntmVerSocios;
	private JMenuItem mntmNuevoSocio;
	private JMenuItem mntmMercaderia;
	private JMenuItem mntmDevoluciones_1;
	private JMenuItem mntmNuevoCliente;
	private JMenuItem mntmNotaDeEntrega;
	private JMenuItem mntmCuentasPagadas;
	private JMenuItem mntmCuentasCobradas;
	private JMenuItem mntmVentasPorSocio;
	private JMenuItem mntmFacturas;
	private JMenuItem mntmNotasDeEntrega;
	private Component rigidArea;
	private Component rigidArea_2;
	private Component rigidArea_3;
	private Component rigidArea_1;

	private JMenuItem mntmCerrarSesion;
	private JMenuItem mntmNuevoUsuario;
	private JPanel panel_;

	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Create the application.
	 */
	public Menu_1() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(UIManager.getColor("Table.selectionBackground"));
		frame.getContentPane().setBackground(UIManager.getColor("TabbedPane.light"));
		frame.setBounds(100, 100, 1033, 676);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Almacen De Ropa");
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("TabbedPane.light"));
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		frame.setJMenuBar(menuBar);

		JMenu mnCuentas = new JMenu("Cuentas");
		mnCuentas.setBackground(UIManager.getColor("TabbedPane.light"));
		mnCuentas.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		menuBar.add(mnCuentas);

		mntmCaja = new JMenuItem("Caja");
		mnCuentas.add(mntmCaja);

		mntmBancos = new JMenuItem("Bancos");
		mnCuentas.add(mntmBancos);

		mntmDepositos = new JMenuItem("Depositos");
		mntmDepositos.setBackground(UIManager.getColor("TabbedPane.light"));
		mnCuentas.add(mntmDepositos);

		mntmCheques = new JMenuItem("Cheques");
		mnCuentas.add(mntmCheques);

		mntmCuentasPorPagar = new JMenuItem("Cuentas por Pagar");
		mnCuentas.add(mntmCuentasPorPagar);

		mntmCuentasPorCobrar = new JMenuItem("Cuentas por Cobrar");
		mnCuentas.add(mntmCuentasPorCobrar);

		JMenu mnNewMenu = new JMenu("Facturacion");
		mnNewMenu.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);

		mnFacturacionNuevaFactura = new JMenuItem("Nueva Factura");
		mnNewMenu.add(mnFacturacionNuevaFactura);

		JMenu mnNewMenu_1 = new JMenu("Socios");
		mnNewMenu_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_1);

		JMenu mnNewMenu_2 = new JMenu("Clientes");
		mnNewMenu_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_2);

		mnClientesVer = new JMenuItem("Ver");
		mnNewMenu_2.add(mnClientesVer);

		JMenu mnNewMenu_4 = new JMenu("Bodega");
		mnNewMenu_4.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_4);

		mnBodegaRevisar = new JMenuItem("Revisar Bodega");
		mnNewMenu_4.add(mnBodegaRevisar);

		JMenu mnNewMenu_5 = new JMenu("Reportes");
		mnNewMenu_5.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_5);

		mnReportesVerVentas = new JMenuItem("Ver Ventas");
		mnReportesVerVentas.setVisible(false);
		mnReportesVerVentas.setEnabled(false);
		mnNewMenu_5.add(mnReportesVerVentas);

		rigidArea = Box.createRigidArea(new Dimension(20, 20));
		frame.getContentPane().add(rigidArea, BorderLayout.NORTH);

		rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		frame.getContentPane().add(rigidArea_1, BorderLayout.SOUTH);

		rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		frame.getContentPane().add(rigidArea_2, BorderLayout.WEST);

		rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		frame.getContentPane().add(rigidArea_3, BorderLayout.EAST);

		panel_ = new JPanel();
		frame.getContentPane().add(panel_, BorderLayout.CENTER);

		mntmNuevoCliente = new JMenuItem("Nuevo Cliente");
		mnNewMenu_2.add(mntmNuevoCliente);

		mntmVerSocios = new JMenuItem("Ver Socios");
		mnNewMenu_1.add(mntmVerSocios);

		mntmNuevoSocio = new JMenuItem("Nuevo Socio");
		mnNewMenu_1.add(mntmNuevoSocio);

		mntmNotaDeEntrega = new JMenuItem("Nota de Entrega (Recibir Mercaderia)");
		mnNewMenu_4.add(mntmNotaDeEntrega);

		mntmCuentasPagadas = new JMenuItem("Cuentas Pagadas");
		mnNewMenu_5.add(mntmCuentasPagadas);

		mntmCuentasCobradas = new JMenuItem("Cuentas Cobradas");
		mnNewMenu_5.add(mntmCuentasCobradas);

		mntmVentasPorSocio = new JMenuItem("Ventas Por Socio");
		mnNewMenu_5.add(mntmVentasPorSocio);

		JMenu mnProveedores = new JMenu("Proveedores");
		mnProveedores.setEnabled(false);
		mnProveedores.setVisible(false);
		mnProveedores.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		menuBar.add(mnProveedores);

		mnProveedoresVer = new JMenuItem("Ver");

		mnProveedores.add(mnProveedoresVer);

		mnProveedoresVer.setActionCommand("Proveedores Ver Proveedores");

		mntmCaja.setActionCommand("Cuentas Caja");
		mntmBancos.setActionCommand("Cuentas Bancos");
		mntmDepositos.setActionCommand("Cuentas Depositos");
		mntmCheques.setActionCommand("Cuentas Cheques");
		mntmCuentasPorPagar.setActionCommand("Cuentas Por Pagar");
		mntmCuentasPorCobrar.setActionCommand("Cuentas Por Cobrar");
		mnFacturacionNuevaFactura.setActionCommand("Facturacion Nueva Factura");
		mntmVerSocios.setActionCommand("Socios Ver Socios");
		mntmNuevoSocio.setActionCommand("Socios Nuevo Socio");

		mntmMercaderia = new JMenuItem("Mercaderia");
		mntmMercaderia.setEnabled(false);
		mntmMercaderia.setVisible(false);
		mnNewMenu_1.add(mntmMercaderia);
		mntmMercaderia.setActionCommand("Socios Mercaderia");
		mnClientesVer.setActionCommand("Clientes Ver Clientes");
		mntmNuevoCliente.setActionCommand("Clientes Nuevo Cliente");
		mnBodegaRevisar.setActionCommand("Bodega Revisar Bodega");
		mntmNotaDeEntrega.setActionCommand("Bodega Nota de Entrega");

		mntmDevoluciones_1 = new JMenuItem("Devoluciones (A socios)");
		mntmDevoluciones_1.setVisible(false);
		mntmDevoluciones_1.setEnabled(false);
		mnNewMenu_4.add(mntmDevoluciones_1);
		mntmDevoluciones_1.setActionCommand("Bodega Devoluciones de Socios");

		mntmDevoluciones = new JMenuItem("Devoluciones De Clientes");
		mnNewMenu_4.add(mntmDevoluciones);
		mntmDevoluciones.setActionCommand("Bodega Devoluciones De Clientes");
		mnReportesVerVentas.setActionCommand("Reportes Ver Ventas");
		mntmCuentasPagadas.setActionCommand("Reportes Cuentas Pagadas");
		mntmCuentasCobradas.setActionCommand("Reportes Cuentas Cobradas");
		mntmVentasPorSocio.setActionCommand("Reportes Ventas Por Socio");

		mntmFacturas = new JMenuItem("Facturas");
		mnNewMenu_5.add(mntmFacturas);

		mntmNotasDeEntrega = new JMenuItem("Notas De Entrega");
		mntmNotasDeEntrega.setVisible(false);
		mntmNotasDeEntrega.setEnabled(false);
		mnNewMenu_5.add(mntmNotasDeEntrega);
		mntmFacturas.setActionCommand("Ver Facturas");
		;
		mntmNotasDeEntrega.setActionCommand("Ver Notas De Entrega");

		JMenu mnUsuario = new JMenu("Usuario");
		mnUsuario.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		menuBar.add(mnUsuario);

		mntmCerrarSesion = new JMenuItem("Cambiar Sesion");
		mntmCerrarSesion.setVisible(false);
		mntmCerrarSesion.setEnabled(false);
		mnUsuario.add(mntmCerrarSesion);

		mntmCerrarSesion.setActionCommand("Cambiar Sesion");

		mntmNuevoUsuario = new JMenuItem("Nuevo Usuario");
		mnUsuario.add(mntmNuevoUsuario);

		mntmNuevoUsuario.setActionCommand("Nuevo Usuario");

	}

	public void setControlador(ControladorVentanaPrincipal controlador) {
		mntmNotasDeEntrega.addActionListener(controlador);
		mntmFacturas.addActionListener(controlador);
		mntmCaja.addActionListener(controlador);
		mntmBancos.addActionListener(controlador);
		mntmDepositos.addActionListener(controlador);
		mntmCheques.addActionListener(controlador);
		mntmCuentasPorPagar.addActionListener(controlador);
		mntmCuentasPorCobrar.addActionListener(controlador);
		mnFacturacionNuevaFactura.addActionListener(controlador);
		mntmDevoluciones.addActionListener(controlador);
		mntmVerSocios.addActionListener(controlador);
		mntmNuevoSocio.addActionListener(controlador);
		mntmMercaderia.addActionListener(controlador);
		mntmDevoluciones_1.addActionListener(controlador);
		mnClientesVer.addActionListener(controlador);
		mntmNuevoCliente.addActionListener(controlador);
		mnBodegaRevisar.addActionListener(controlador);
		mntmNotaDeEntrega.addActionListener(controlador);
		mnReportesVerVentas.addActionListener(controlador);
		mntmCuentasPagadas.addActionListener(controlador);
		mntmCuentasCobradas.addActionListener(controlador);
		mntmVentasPorSocio.addActionListener(controlador);

		mntmNuevoUsuario.addActionListener(controlador);
		mntmCerrarSesion.addActionListener(controlador);

		mnProveedoresVer.addActionListener(controlador);

	}

	//public void setControl(ControladorVentanaPrincipal controladorPrincipal) {
		
		//this.controladorVistaPrincipal = controladorPrincipal;
		
	//}

	

}
