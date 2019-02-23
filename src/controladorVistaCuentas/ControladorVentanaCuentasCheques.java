package controladorVistaCuentas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import conexion.AbstractReports;
import controladorCRUD.ControladorCRUD_DAO;
import controladorCuentas.ControladorCuentasChequesDAO;
import controladorEstructuraDeDatos.ControladorListasYArboles;
import controladorFacturacion.TablasTemporales;
import controladorSocios.ControladorSociosVerSociosDAO;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.Cheque;
import modeloEntidades.VistaCaja;
import modeloEntidades.VistaCheque;
import modeloEntidades.VistaCuenta;
import modeloEntidades.VistaCuentasPorCobrar;
import pruebas.Iniciador;
import vista.Menu_1;
import vistaCuentas.PanelCheques;
import vistaCuentas.PanelNuevoCheque;

public class ControladorVentanaCuentasCheques extends MouseAdapter implements ActionListener {

	private Menu_1 vista;
	private String rol;
	public ControladorVentanaCuentasCheques(Menu_1 vista, String rol) {
		super();
		this.vista = vista;
		this.rol = rol;
	}

	public void mousePressed(MouseEvent mouse) {

		PanelCheques xy = (PanelCheques) mouse.getComponent().getParent().getParent().getParent().getParent();

		if (xy.getName().toString().equals("PANEL DE CHEQUES - DEPOSITOS")) {
			if (mouse.getClickCount() == 2) {

				VistaCheque cheque = null;

				try {
					cheque = ControladorCuentasChequesDAO
							.buscarCheque(Integer.parseInt(PanelCheques.getPanelCheques().table
									.getValueAt(PanelCheques.getPanelCheques().table.getSelectedRow(), 0).toString()));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String x = "Numero de cheque: " + cheque.getNCheque() + "\n" + "Titular: " + cheque.getTitularCuenta()
						+ "\n" + "Banco: " + cheque.getChequeBanco() + "\n" + "Ingreso: " + cheque.getFechaIngreso()
						+ "\n" + "Deposito: " + cheque.getFechaDeposito() + "\n" + "Valor: " + cheque.getValor() + "\n";

				int opc = JOptionPane.showConfirmDialog(null, x + "¿Desea ingresar el cheque?", "Igresar Cheque",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				if (opc == JOptionPane.OK_OPTION) {

					vista.frameDeposito.txtIdCheque.setText(String.valueOf(cheque.getIdcheque()));
					vista.frameDeposito.txtCheque.setText(String.valueOf(cheque.getValor()));
					
					vista.frameDeposito.txtTotal.setText(vista.frameDeposito.txtCheque.getText());
					if(!vista.frameDeposito.txtEfectivo.getText().equals("")) {
						double valor1 = Double.parseDouble(vista.frameDeposito.txtEfectivo.getText());
						double valor2 = Double.parseDouble(vista.frameDeposito.txtCheque.getText());
						vista.frameDeposito.txtTotal.setText(String.valueOf(valor1+valor2));
						
					}
					
					PanelCheques.getNuevo().dispose();
					vista.frameDeposito.getBtnBuscarCheque().setEnabled(true);
					
				} else if (opc == JOptionPane.CLOSED_OPTION || opc == JOptionPane.CANCEL_OPTION) {

				}

			}
		} else {

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Actualizar cheque")) {

			System.out.println("Actualizar cheque");

		} else if (e.getActionCommand().equals("buscar cheques por fecha")) {

			try {
				if (!vista.internalFrameCuentasCheques.getPanel().dateChooserFechaBusqueda.getDate().toString()
						.equals("")) {
					ArbolAVL<VistaCheque> lista = buscarFecha(
							vista.internalFrameCuentasCheques.getPanel().dateChooserFechaBusqueda.getDate());
	
					DefaultTableModel mod = mostrarTablaVistaCheques(lista, "TODOS");
					vista.internalFrameCuentasCheques.getPanel().table.setModel(mod);
	
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese una Fecha");
				}
			}catch(NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "Compruebe que los campos estén con parámetros","*** AVISO ***",JOptionPane.INFORMATION_MESSAGE);
			}

		} else if (e.getActionCommand().equals("buscar cheques por rango de fechas")) {

			try {
				if (!vista.internalFrameCuentasCheques.getPanel().dateChooserFechaDesde.getDate().toString().equals("")
						&& !vista.internalFrameCuentasCheques.getPanel().dateChooserFechaHasta.getDate().toString()
								.equals("")) {
	
					ArbolAVL<VistaCheque> lista = buscarRangoFechas(
	
							vista.internalFrameCuentasCheques.getPanel().dateChooserFechaDesde.getDate(),
	
							vista.internalFrameCuentasCheques.getPanel().dateChooserFechaHasta.getDate());
	
					DefaultTableModel mod = mostrarTablaVistaCheques(lista, "TODOS");
					vista.internalFrameCuentasCheques.getPanel().table.setModel(mod);
	
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese las fechas 'Rango de busqueda'");
				}
			}catch(NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "Compruebe que los campos estén con parámetros","*** AVISO ***",JOptionPane.INFORMATION_MESSAGE);
			}

		} else if (e.getActionCommand().equals("eliminar cheque") && rol.equals("ADMINISTRADOR")) {

			System.out.println("ellimminar cheque");

		} else if (e.getActionCommand().equals("ingresar cheque")) {

			System.out.println("ingresar cheque");

		} else if (e.getActionCommand().equals("nuevo cheque")) {

			nuevoCheque();

		} else if (e.getActionCommand().equals("Actualizar")) {
			actualizarTabla();
		}else if (e.getActionCommand().equals("Generar Reporte")) {
			generarReporte();
		}
		
	}

	private void generarReporte() {
		
conexion.Conexion con = new conexion.Conexion();
		
		try(Connection connection = con.ObtenerConexion()){
			
			AbstractReports.crearReporte(connection, "src\\ReportesAlmacen\\Cheques.jasper");
			AbstractReports.showViewer();
		
		
		
		}catch (SQLException ex) {
			
		ex.printStackTrace();
			ex.getMessage();
		
		}

		
	}

	public void actualizarTabla() {

		DefaultTableModel mod;
		mod = mostrarTablaVistaCheques(ControladorListasYArboles.arbolVistaCheques, "TODOS");
		vista.internalFrameCuentasCheques.getPanel().table.setModel(mod);
	}

	public static DefaultTableModel mostrarTablaVistaCheques(ArbolAVL<VistaCheque> arbol, String estado) {
		String[] titulos = { "Id", "Num.Cheque", "Cheque Banco", "Titular Cuenta", "Fecha Ingreso", "Fecha Deposito",
				"Valor", "Descripcion", "Num.Factura" };
		String[] registro = new String[9];
		DefaultTableModel modelo = new DefaultTableModel(null, titulos);
		for (VistaCheque oe : arbol.inOrdenList()) {

			registro[0] = String.valueOf(oe.getIdcheque());
			registro[1] = String.valueOf(oe.getNCheque());
			registro[2] = oe.getChequeBanco();
			registro[3] = oe.getTitularCuenta();
			registro[4] = String.valueOf(oe.getFechaIngreso());
			registro[5] = String.valueOf(oe.getFechaDeposito());
			registro[6] = String.valueOf(oe.getValor());
			registro[7] = oe.getDescripcion();
			registro[8] = oe.getNFactura();
			if (estado.equals("TODOS")) {
				modelo.addRow(registro);
			} else if (oe.getDescripcion().equals("PENDIENTE") && estado.equals("PENDIENTE")) {
				modelo.addRow(registro);
			} else if (oe.getDescripcion().equals("DEPOSITADO") && estado.equals("DEPOSITADO")) {
				modelo.addRow(registro);
			}

		}
		return modelo;
	}

	public void nuevoCheque() {
		VistaCuentasPorCobrar cuenta = null;
		String opc = JOptionPane.showInputDialog(null, "Ingrese el numero de factura a pagar:", "NUEVO CHEQUE",
				JOptionPane.PLAIN_MESSAGE);
		if (opc == null) {

		} else if (!opc.equals("")) {

			try {
				cuenta = ControladorListasYArboles.controladorCuentasCuentasPorCobrarDAO.getCuentaPorCobrar(opc);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			PanelNuevoCheque pnc = new PanelNuevoCheque();

			pnc.lbllNfact.setText("Factura N° " + cuenta.getNumerofactura());
			pnc.lblNumeroDeFactura.setText("Saldo pendiente: $" + cuenta.getValorpendiente());

			while (true) {
				int resultado = JOptionPane.showConfirmDialog(null, pnc, "Ingrese Nuevo Cheque",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				if (resultado == JOptionPane.OK_OPTION && !pnc.txtNumCheque.getText().equals("")
						&& !pnc.txtChequeBanco.getText().equals("") && !pnc.txtTitularCuenta.getText().equals("")
						&& !pnc.dateChooserIngreso.getDate().equals("") && !pnc.dateChooserDeposito.getDate().equals("")
						&& !pnc.txtValor.getText().equals("")) {

					Integer num_cheque = Integer.parseInt(pnc.txtNumCheque.getText());
					String cheque_banco = pnc.txtChequeBanco.getText();
					String titular_cuenta = pnc.txtTitularCuenta.getText();

					Date fechaIngreso = Date.valueOf(TablasTemporales.asLocalDate(pnc.dateChooserIngreso.getDate()));
					Date fechaDeposito = Date.valueOf(TablasTemporales.asLocalDate(pnc.dateChooserDeposito.getDate()));

					String descripcion = "PENDIENTE";
					Double valor = Double.parseDouble(pnc.txtValor.getText());

					Cheque cheque = new Cheque(cheque_banco, descripcion, fechaDeposito, fechaIngreso, num_cheque,
							cuenta.getIdcuentaPorCobrar(), titular_cuenta, valor);

					ControladorCRUD_DAO cont = new ControladorCRUD_DAO();
					cont.insertarSGBD(cheque);

					ControladorListasYArboles.actualizarArbolCheques(vista);
					actualizarTabla();

					return;
					//
					// Cuando SELECCIONE ACEPTAR Y GUARDAR CONTACTO ESTA LISTA SE DEBE VACIAR;
					//

				} else if (resultado == JOptionPane.CANCEL_OPTION || resultado == JOptionPane.CLOSED_OPTION) {
					return;
				} else {
					JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Falta Infotmacion",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		}
	}

	private ArbolAVL<VistaCheque> buscarFecha(java.util.Date fecha) {

		ArbolAVL<VistaCheque> arbol = ControladorListasYArboles.arbolVistaCheques;
		ArbolAVL<VistaCheque> listaResultado = new ArbolAVL<>();

		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String aux = (dt.format(fecha));

		try {
			fecha = dt.parse(aux);

		} catch (ParseException e) {

			e.printStackTrace();
		}

		for (int i = 0; i < arbol.size(); i++) {
			if (arbol.getPreOrden(i).getFechaDeposito().compareTo(fecha) == 0) {
				listaResultado.add(arbol.getPreOrden(i));
			}

		}

		return listaResultado;
	}

	private ArbolAVL<VistaCheque> buscarRangoFechas(java.util.Date fecha1, java.util.Date fecha2) {

		ArbolAVL<VistaCheque> arbol = ControladorListasYArboles.arbolVistaCheques;
		ArbolAVL<VistaCheque> listaResultado = new ArbolAVL<>();

		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String aux = (dt.format(fecha1));
		String aux2 = (dt.format(fecha2));
		try {
			fecha1 = dt.parse(aux);
			fecha2 = dt.parse(aux2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < arbol.size(); i++) {
			if (arbol.getPreOrden(i).getFechaDeposito().compareTo(fecha1) >= 0
					&& arbol.getPreOrden(i).getFechaDeposito().compareTo(fecha2) <= 0) {
				listaResultado.add(arbol.getPreOrden(i));
			}

		}

		return listaResultado;
	}

}
