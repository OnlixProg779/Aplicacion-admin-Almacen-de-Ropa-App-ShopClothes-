package controladorVistaCuentas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controladorCRUD.ControladorCRUD_DAO;
import controladorEstructuraDeDatos.ControladorListasYArboles;
import controladorFacturacion.TablasTemporales;
import estructuraDeDatos.ArbolAVL;
import modeloEntidades.CuentasPorPagar;
import modeloEntidades.VistaCaja;
import modeloEntidades.VistaCheque;
import pruebas.Iniciador;
import vista.Menu_1;
import vistaCuentas.PanelNuevaCuentaPorPagar;

public class ControladorVentanaCuentasPorPagar implements ActionListener {

	private Menu_1 vista;

	public ControladorVentanaCuentasPorPagar(Menu_1 vista) {
		super();
		this.vista = vista;
	}

	int posicion;

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("abonar a la cuenta")) {

			System.out.println("abonar a la cuenta");

		} else if (e.getActionCommand().equals("buscar en rango")) {

			try {
				if (!vista.internalFrameCuentasPorPagar.getPanel().dateChooserFechaDesde.getDate().toString().equals("")
						&& !vista.internalFrameCuentasPorPagar.getPanel().dateChooserFechaHasta.getDate().toString()
								.equals("")) {
	
					ArbolAVL<CuentasPorPagar> lista = buscarRangoFechas(
							java.sql.Date.valueOf(TablasTemporales.asLocalDate(
									vista.internalFrameCuentasPorPagar.getPanel().dateChooserFechaDesde.getDate())),
							java.sql.Date.valueOf(TablasTemporales.asLocalDate(
									vista.internalFrameCuentasPorPagar.getPanel().dateChooserFechaHasta.getDate())));
					
					DefaultTableModel mod = ControladorListasYArboles.controladorCuentasCuentasPorPagarDAO.mostrarTablaCuentasPorPagar(lista);
					vista.internalFrameCuentasPorPagar.getPanel().table.setModel(mod);
	
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese las fechas 'Rango de busqueda'");
				}
			}catch(NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "Compruebe que los campos estén con parámetros","*** AVISO ***",JOptionPane.INFORMATION_MESSAGE);
			}

		} else if (e.getActionCommand().equals("Pagar Cuenta")) {
			if (vista.internalFrameCuentasPorPagar.getPanel().table.getSelectedRow() != -1) {

				posicion = Integer.parseInt((vista.internalFrameCuentasPorPagar.getPanel().table
						.getValueAt(vista.internalFrameCuentasPorPagar.getPanel().table.getSelectedRow(), 0)
						.toString()));
				pagarCuenta(posicion);
				

			} else {
				JOptionPane.showMessageDialog(null, "Seleccione un registro de la tabla", "** ERROR **",
						JOptionPane.WARNING_MESSAGE);
			}

		} else if (e.getActionCommand().equals("Nueva Cuenta")) {

			nuevaCuentaPorPagar();

		} else if (e.getActionCommand().equals("Actualizar")) {

			actualizarTabla();
		}

	}

	private void pagarCuenta(int idCuenta) {
		int resultado = JOptionPane.showConfirmDialog(null, "Pago de: "
				+ (vista.internalFrameCuentasPorPagar.getPanel().table
						.getValueAt(vista.internalFrameCuentasPorPagar.getPanel().table.getSelectedRow(), 3).toString())
				+ "\n Por: $ "
				+ (vista.internalFrameCuentasPorPagar.getPanel().table
						.getValueAt(vista.internalFrameCuentasPorPagar.getPanel().table.getSelectedRow(), 2).toString())
				+ "\n ¿Desea continuar?", "** REGISTRAR PAGO **", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (resultado == JOptionPane.OK_OPTION) {
			try {
				if (ControladorListasYArboles.controladorCuentasCuentasPorPagarDAO.actualizarCuenta(idCuenta)) {
					JOptionPane.showMessageDialog(null, "Pago Registrado");
					Iniciador.controladorPrincipal.openCuentasPorPagar();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (resultado == JOptionPane.CANCEL_OPTION || resultado == JOptionPane.CLOSED_OPTION) {
			JOptionPane.showMessageDialog(null, "Transaccion Cancelada", "*AVISO*", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void nuevaCuentaPorPagar() {
		Date fecha;
		double valor;
		String descripcion;

		vistaCuentas.PanelNuevaCuentaPorPagar panel = new PanelNuevaCuentaPorPagar();
		while (true) {
			int resultado = JOptionPane.showConfirmDialog(null, panel, "** NUEVA CUENTA **",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

			if (resultado == JOptionPane.OK_OPTION && !panel.txtCuenta.getText().equals("")
					&& !panel.txtDescripcion.getText().equals("")) {

				fecha = (panel.dateChooserFecha.getDate());

				System.out.println(fecha);

				valor = Double.parseDouble(panel.txtCuenta.getText());
				descripcion = panel.txtDescripcion.getText();

				int resultado2 = JOptionPane.showConfirmDialog(null,
						"Registrando " + descripcion + "\n Por: $ " + valor + "\n" + "¿Desean continuar?", "AVISO",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				if (resultado2 == JOptionPane.OK_OPTION) {

					System.out.println(descripcion + "\n" + fecha.toString() + "\n" + valor);

					CuentasPorPagar cuentadepagar = new CuentasPorPagar(descripcion, "PENDIENTE", fecha, valor);

					ControladorCRUD_DAO cont = new ControladorCRUD_DAO();
					cont.insertarSGBD(cuentadepagar);

					Iniciador.controladorPrincipal.openCuentasPorPagar();

				} else if (resultado2 == JOptionPane.CANCEL_OPTION || resultado2 == JOptionPane.CLOSED_OPTION) {
					return;
				}

				return;

			} else if (resultado == JOptionPane.CANCEL_OPTION || resultado == JOptionPane.CLOSED_OPTION) {
				return;
			} else {
				JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Falta Información",
						JOptionPane.WARNING_MESSAGE);
			}
		}

	}
	
	private ArbolAVL<CuentasPorPagar> buscarFecha(java.util.Date fecha) {

		ArbolAVL<CuentasPorPagar> arbol = ControladorListasYArboles.arbolVistaCXP;
		ArbolAVL<CuentasPorPagar> listaResultado = new ArbolAVL<>();
		
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String aux = (dt.format(fecha));
	
		try {
			fecha = dt.parse(aux);
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for (int i = 0; i < arbol.size(); i++) {
			if (arbol.getPreOrden(i).getFecha().compareTo(fecha) == 0) {
				listaResultado.add(arbol.getPreOrden(i));
			}

		}

		return listaResultado;
	}

	private ArbolAVL<CuentasPorPagar> buscarRangoFechas(java.util.Date fecha1, java.util.Date fecha2) {

		ArbolAVL<CuentasPorPagar> arbol = ControladorListasYArboles.arbolVistaCXP;
		ArbolAVL<CuentasPorPagar> listaResultado = new ArbolAVL<>();
		
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
			if (arbol.getPreOrden(i).getFecha().compareTo(fecha1) >= 0
					&& arbol.getPreOrden(i).getFecha().compareTo(fecha2) <= 0) {
				listaResultado.add(arbol.getPreOrden(i));
			}

		}

		return listaResultado;
	}
	

	public void actualizarTabla() {
		DefaultTableModel mod;
		mod = ControladorListasYArboles.controladorCuentasCuentasPorPagarDAO
				.mostrarTablaCuentasPorPagar(ControladorListasYArboles.arbolVistaCXP);
		vista.internalFrameCuentasPorPagar.getPanel().table.setModel(mod);
	}

}
